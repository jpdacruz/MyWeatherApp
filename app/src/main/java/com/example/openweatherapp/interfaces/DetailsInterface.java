package com.example.openweatherapp.interfaces;

import android.content.Context;

import com.example.openweatherapp.clases.DataCity;

public interface DetailsInterface {

    interface view{

        void showResult(DataCity dataCity);
    }

    interface presenter{

        void showResult(DataCity dataCity);
        void getCityDetails(String nameCity,Context context);
    }

    interface model {

        void getCityDetails(String nameCity, Context context);
    }
}
