package com.example.reallyenglsh.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.reallyenglsh.MyWordList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {
    private static SentencesDatabase database;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = SentencesDatabase.getInstance(getApplication());
    }


    public void insertCoupleList(CoupleList myListCouples) {
        new InsertCoupleListTask().execute(myListCouples);
    }
    private static class InsertCoupleListTask extends AsyncTask<CoupleList, Void, Void> {
        @Override
        protected Void doInBackground(CoupleList... myListCouples) {
            CoupleList savedCoupleList = myListCouples[0];
            database.coupleListDao().insertMyListCouple(savedCoupleList);
            return null;
        }
    }

    public void insertListOfCoupleList(List<CoupleList> listOfCoupleList) {
        new InsertListOfCoupleList().execute(listOfCoupleList);
    }
    private static class InsertListOfCoupleList extends AsyncTask<List<CoupleList>, Void, Void> {
        @Override
        protected Void doInBackground(List<CoupleList>... lists) {
            database.coupleListDao().insertListOfCoupleLists(lists[0]);
            return null;
        }
    }

    public List<CoupleList> getListOfSavedCoupleListInDB() {
        List<CoupleList> result = new ArrayList<>();
        try {
            result =  new GetListSavedCoupleListFromDBTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static class GetListSavedCoupleListFromDBTask extends AsyncTask<Void, Void, List<CoupleList>> {
        @Override
        protected List<CoupleList> doInBackground(Void... voids) {
            return database.coupleListDao().getAllSavedCoupleLists();
        }
    }

    public boolean isDBSavedCoupleListsEmpty() {
        boolean result = false;
        try {
            result = new IsDBSavedCoupleListsEmptyTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static class IsDBSavedCoupleListsEmptyTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return database.coupleListDao().getCount() == 0;
        }
    }

    public void clearDbSavedCoupleLists() {
        new ClearDBSavedCoupleLists().execute();
    }
    private static class ClearDBSavedCoupleLists extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            database.coupleListDao().clearDBSavedCoupleLists();
            return null;
        }
    }




    public void insertSentence(Sentence sentence) {
        new InsertTask().execute(sentence);
    }

    public int getMaxId() {
        int result = -1;
        try {
            result = new GetMaxIdTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getCountSentences() {
        int result = -1;
        try {
            result = new GetCountSentencesTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isSentenceInDB(String textSentence) {
        boolean result = false;
        try {
            return  new IsSentenceInDBTask().execute(textSentence).get();
        } catch (ExecutionException | InterruptedException e) {
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


    public void insertV3Verb(V3Verb verb) {
        new InsertV3VerbTask().execute(verb);
    }

    public int getMaxIdOfV3Verb() {
        int result = -1;
        try {
            result = new GetMaxIdOfV3VerbTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getCountV3Verbs() {
        int result = -1;
        try {
            result = new GetCountV3VerbsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isV3VerbInDB(String verb) {
        boolean result = false;
        try {
            result = new IsV3VerbInDBTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class GetMaxIdOfV3VerbTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return database.v3VerbDao().getMaxId();
        }
    }

    private static class InsertV3VerbTask extends AsyncTask<V3Verb, Void, Void> {
        @Override
        protected Void doInBackground(V3Verb... v3Verbs) {
            if (v3Verbs != null && v3Verbs.length > 0) {
                database.v3VerbDao().insertVerb(v3Verbs[0]);
            }
            return null;
        }
    }

    private static class GetCountV3VerbsTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return database.v3VerbDao().getCount();
        }
    }

    private static class IsV3VerbInDBTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            return database.v3VerbDao().isV3VerbInDB(strings[0]);
        }
    }

    public void insertListOfMyListWords(List<MyWordList> lists) {
        new InsertListOfMyListWordsTask().execute(lists);
    }
    private static class InsertListOfMyListWordsTask extends AsyncTask<List<MyWordList>, Void, Void> {
        @Override
        protected Void doInBackground(List<MyWordList>... lists) {
            database.myWordListDao().insertListOfMyWordList(lists[0]);
            return null;
        }
    }

    public List<MyWordList> getMyWordLists() {
        List<MyWordList> result = null;
        try {
            result = new GetMyWordListsTask().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static class GetMyWordListsTask extends AsyncTask<Void, Void, List<MyWordList>> {
        @Override
        protected List<MyWordList> doInBackground(Void... voids) {
            return database.myWordListDao().getAllMyWordLists();
        }
    }
}
