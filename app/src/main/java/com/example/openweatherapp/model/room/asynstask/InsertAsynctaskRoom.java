package com.example.openweatherapp.model.room.asynstask;

import android.os.AsyncTask;

import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.model.room.WeatherCityDao;

public class InsertAsynctaskRoom extends AsyncTask<CityEntity, Void, Void> {

    private WeatherCityDao cityDaoAsyncTask;

    public InsertAsynctaskRoom(WeatherCityDao dao) {
        this.cityDaoAsyncTask = dao;
    }

    @Override
    protected Void doInBackground(CityEntity... cityEntities) {
        cityDaoAsyncTask.insert(cityEntities[0]);
        return null;
    }
}
