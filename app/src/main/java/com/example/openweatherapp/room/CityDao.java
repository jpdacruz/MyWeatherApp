package com.example.openweatherapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insert(CityEntity cityEntity);

    @Query("DELETE FROM cities WHERE id = :idCity")
    void deleteCityFav(int idCity);

    @Query("SELECT * FROM cities ORDER BY nameCity ASC")
    LiveData<List<CityEntity>> getCitiesFav();
}
