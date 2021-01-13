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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return content.toString();
    }

    public String getTranslation (String loadedContent) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("<div class=\"t_inline_en\">([а-я,\\s]*)</div>");
        Matcher matcher = pattern.matcher(loadedContent);

        while (matcher.find()) {
            result.append(matcher.group(1));
        }
        return result.toString();
    }

    public String getTranscription(String loadedContent) {
        StringBuilder result = new StringBuilder();
        String word = BUNDLE.getString("word");

        String s = String.format("<span\\stitle=\"американская\\sтранскрипция\\sслова\\s%s\"\\sclass=\"transcription\">\\s(\\Q|\\E\\w+\\Q|\\E)</span>", word);
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(loadedContent);

        while (matcher.find()) {
            result.append(matcher.group(1));
        }
        return result.toString();
    }

//    public String getResultByIndex(String loadedContent) {
//        if (index == INDEX_GET_TRANSLATION_METHOD) {
//            return getTranslation(loadedContent);
//        } else if (index == INDEX_GET_TRANSCRIPTION_METHOD){
//            return getTranscription(loadedContent);
//        } else {
//            return "The given index of method is incorrect";
//        }
//    }
}
