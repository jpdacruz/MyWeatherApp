package com.example.openweatherapp.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.model.room.RoomRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private LiveData<List<CityEntity>> listCityFav;
    private RoomRepository roomRepository;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);

        roomRepository = new RoomRepository(application);
        listCityFav = roomRepository.getFavCities();
    }

    //method to return all cities
    public LiveData<List<CityEntity>> getListCityFavs(){

        return listCityFav;
    }

    //method to insert new favorite city
    public void insertCityFav(CityEntity newCityFav){

        roomRepository.insertFavCity(newCityFav);
    }

    //method to delete a favorite city
    public void deleteCityFav(String delCityFavId){

        roomRepository.deleteCityFav(delCityFavId);
    }
}