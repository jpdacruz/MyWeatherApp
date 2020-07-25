package com.example.openweatherapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.openweatherapp.utils.MyApp;
import com.example.openweatherapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class ListCityRepository {

    private static final String TAG = "ListCitiesRepository";
    private MutableLiveData<List<String>> mCityMutableList;
    private ArrayList<String> mCityArrayList;

    public ListCityRepository() {

        this.mCityMutableList = new MutableLiveData<>();
        this.mCityArrayList = new ArrayList<>();
    }

    public MutableLiveData<List<String>> getmCityMutableList() {
        try {

            StringBuilder stringBuilder = new StringBuilder();

            //add MyApp clase to the proyect to get Context for getResources. Bad practice, I know it!
            InputStreamReader reader = new InputStreamReader(new GZIPInputStream(MyApp.getContext().getResources().openRawResource(R.raw.city_list)));
            BufferedReader in = new BufferedReader(reader);

            String citydata;
            while ((citydata = in.readLine())!= null)
                stringBuilder.append(citydata);
            mCityArrayList = new Gson().fromJson(stringBuilder.toString(), new TypeToken<List<String>>(){}.getType());
            mCityMutableList.setValue(mCityArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mCityMutableList;
    }

    //to test Repository
    private void getCities() {

        String city1 ="Buenos Aires, Ar";
        String city2 = "Cordoba, Ar";
        mCityArrayList.add(city1);
        mCityArrayList.add(city2);
        mCityMutableList.setValue(mCityArrayList);
    }
}
