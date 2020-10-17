package com.example.realyenglsh;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static SentencesDatabase database;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = SentencesDatabase.getInstance(getApplication());
    }

    public void insertSentence(Sentence sentence) {
        new InsertTask().execute(sentence);
    }

    public int getMaxId() {
        int result = -1;
        try {
            result = new GetMaxIdTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getCountSentences() {
        int result = -1;
        try {
            result = new GetCountSentencesTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isSentenceInDB(String textSentence) {
        boolean result = false;
        try {
            result = new IsSentenceInDBTask().execute(textSentence).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class InsertTask extends AsyncTask<Sentence, Void, Void> {
        @Override
        protected Void doInBackground(Sentence... sentences) {
            if (sentences != null && sentences.length > 0) {
                database.sentencesDao().insertSentence(sentences[0]);
            }
            return null;
        }
    }

    private static class GetMaxIdTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return database.sentencesDao().getMaxId();
        }
    }

    private static class GetCountSentencesTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return database.sentencesDao().getCountSentences();
        }
    }

    private static class IsSentenceInDBTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            return database.sentencesDao().isSentenceInDB(strings[0]);
        }
    }
}
