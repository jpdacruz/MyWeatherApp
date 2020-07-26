package com.example.openweatherapp.model.room;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.model.room.asynstask.DeleteAsynctaskRoom;
import com.example.openweatherapp.model.room.asynstask.InsertAsynctaskRoom;

import java.util.List;

public class RoomRepository {

    private WeatherCityDao cityDao;
    private LiveData<List<CityEntity>> listFavCities;

    public RoomRepository(Application application){
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        cityDao = db.weatherCityDao();
        listFavCities = cityDao.getFavCities();
    }

    public LiveData<List<CityEntity>> getFavCities(){
        return  listFavCities;
    }

    public void insertFavCity (CityEntity cityEntity){
        new InsertAsynctaskRoom(cityDao).execute(cityEntity);
    }

    public void deleteCityFav (String iDcity){
        new DeleteAsynctaskRoom(cityDao).execute(iDcity);
    }
}
