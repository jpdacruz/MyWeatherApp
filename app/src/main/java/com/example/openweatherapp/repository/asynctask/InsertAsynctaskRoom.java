package com.example.openweatherapp.repository.asynctask;

import android.os.AsyncTask;

import com.example.openweatherapp.room.CityDao;
import com.example.openweatherapp.room.CityEntity;

public class InsertAsynctaskRoom extends AsyncTask<CityEntity, Void, Void> {

    private CityDao cityDaoAsyncTask;

    public InsertAsynctaskRoom(CityDao dao) {
        this.cityDaoAsyncTask = dao;
    }

    @Override
    protected Void doInBackground(CityEntity... cityEntities) {
        cityDaoAsyncTask.insert(cityEntities[0]);
        return null;
    }
}
