package com.example.openweatherapp.model;

import com.example.openweatherapp.model.entity.CityEntity;

public interface DetailsInterface {

    interface view{

        void showResult(CityEntity cityEntity,int codResult);
    }

    interface presenter {

        void showResult(CityEntity cityEntity, int codResult);
        void getCityDetails(String nameCity);
    }

    interface repository {

        void getCityDetails(String nameCity);
    }
}
