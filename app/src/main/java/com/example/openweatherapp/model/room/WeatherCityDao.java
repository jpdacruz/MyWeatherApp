package com.example.openweatherapp.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.openweatherapp.model.entity.CityEntity;

import java.util.List;

@Dao
public interface WeatherCityDao {

    @Insert
    void insert(CityEntity cityEntity);

    @Query("DELETE FROM weather_city WHERE idCity = :idCity")
    void deleteById(String idCity);

    @Query("SELECT * FROM weather_city ORDER BY nameCity ASC")
    LiveData<List<CityEntity>>getFavCities();
}
