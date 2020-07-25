package com.example.openweatherapp.ui.favcities;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.openweatherapp.repository.RoomRepository;
import com.example.openweatherapp.room.CityEntity;

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

    //method to inster new favorite city
    public void insertCityFav(CityEntity newCityFav){

        roomRepository.insertFavCity(newCityFav);
    }
}