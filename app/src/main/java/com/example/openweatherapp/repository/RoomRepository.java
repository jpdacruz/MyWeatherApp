package com.example.openweatherapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.openweatherapp.room.CityDao;
import com.example.openweatherapp.room.CityEntity;
import com.example.openweatherapp.room.CityRoomDatabase;
import com.example.openweatherapp.repository.asynctask.InsertAsynctaskRoom;

import java.util.List;

public class RoomRepository {

    private CityDao cityDao;
    private LiveData<List<CityEntity>> listFavCities;

    public RoomRepository(Application application){
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        cityDao = db.cityDao();
        listFavCities = cityDao.getCitiesFav();
    }

    public LiveData<List<CityEntity>> getFavCities(){
        return  listFavCities;
    }

    public void insertFavCity (CityEntity cityEntity){
        new InsertAsynctaskRoom(cityDao).execute(cityEntity);
    }

    public void deleteCityFav (int iDcity){
        cityDao.deleteCityFav(iDcity);
    }
}
