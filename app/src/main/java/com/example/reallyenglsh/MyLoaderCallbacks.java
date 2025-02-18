package com.example.reallyenglsh;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class MyLoaderCallbacks implements LoaderManager.LoaderCallbacks<String> {
    private final Context context;
    private final int id;
    private IOnCallbackHelper helper;

    public MyLoaderCallbacks(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    public void setHelper(IOnCallbackHelper helper) {
        this.helper = helper;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return helper.onCreateLoader(args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (helper != null) {
            helper.onLoadFinished(loader, data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
