package com.example.reallyenglsh;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownLoader extends AsyncTaskLoader<String> {
    private final Bundle BUNDLE;
    private final String MAIN_URL = "https://wooordhunt.ru/word/";

    public DownLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        this.BUNDLE = bundle;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        if (BUNDLE == null) {
            return "Bundle is empty";
        }

        String path = MAIN_URL + BUNDLE.getString("word");
        StringBuilder content = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            URL url = new URL(path);
            if (url == null) {
                return "URL is empty";
            }
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while (line != null) {
                content.append(line);
                line = bufferedReader.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return getTranslation(content.toString());
    }

    private String getTranslation (String loadedContent) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("<span class=\"t_inline_en\">([а-я,\\s]*)</span>");
        Matcher matcher = pattern.matcher(loadedContent);

        while (matcher.find()) {
            result.append(matcher.group(1));
        }
        return result.toString();
    }
}
