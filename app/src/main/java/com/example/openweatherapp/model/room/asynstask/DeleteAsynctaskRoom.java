package com.example.openweatherapp.model.room.asynstask;

import android.os.AsyncTask;

import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.model.room.WeatherCityDao;

public class DeleteAsynctaskRoom extends AsyncTask<String,Void,Void> {

    private WeatherCityDao cityDaoAsyncTask;

    public DeleteAsynctaskRoom(WeatherCityDao dao) {
        this.cityDaoAsyncTask = dao;
    }


    @Override
    protected Void doInBackground(String... strings) {
        cityDaoAsyncTask.deleteById(strings[0]);
        return null;
    }
}
