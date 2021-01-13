package com.example.reallyenglsh;

import android.os.Bundle;

import androidx.loader.content.Loader;

import javax.annotation.Nullable;

public interface IOnCallbackHelper {
    Loader<String> onCreateLoader(@Nullable Bundle args);
    void onLoadFinished(Loader<String> loader, String data);
}
