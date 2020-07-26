package com.example.openweatherapp.model.repository;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.openweatherapp.R;
import com.example.openweatherapp.utils.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class SearchRepository {

    private static final String TAG = "SearchRepository";
    private MutableLiveData<List<String>> mCityMutableList;
    private ArrayList<String> mCityArrayList;

    public SearchRepository() {

        this.mCityMutableList = new MutableLiveData<>();
        this.mCityArrayList = new BackgroundTask().doInBackground();
    }

    public MutableLiveData<List<String>> getmCityMutableList() {

        mCityMutableList.setValue(mCityArrayList);

        return mCityMutableList;
    }

    private class BackgroundTask extends AsyncTask<Void,Void,ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            try {
                StringBuilder stringBuilder = new StringBuilder();

                //add MyApp clase to the proyect to get Context for getResources. Bad practice, I know it!
                InputStreamReader reader = new InputStreamReader
                        (new GZIPInputStream(MyApp.getContext()
                                .getResources()
                                .openRawResource(R.raw.city_list)));

                BufferedReader in = new BufferedReader(reader);

                String citydata;
                while ((citydata = in.readLine())!= null)
                    stringBuilder.append(citydata);
                mCityArrayList = new Gson().fromJson(stringBuilder.toString(), new TypeToken<List<String>>(){}.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return mCityArrayList;
        }
    }
}
