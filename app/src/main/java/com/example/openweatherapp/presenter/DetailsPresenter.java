package com.example.openweatherapp.presenter;

import android.content.Context;

import com.example.openweatherapp.utils.DataCity;
import com.example.openweatherapp.interfaces.DetailsInterface;
import com.example.openweatherapp.model.DetailsModel;

public class DetailsPresenter implements DetailsInterface.presenter {

    private DetailsInterface.view view;
    private DetailsInterface.model model;

    public DetailsPresenter(DetailsInterface.view view) {
        this.view = view;
        this.model = new DetailsModel(this);
    }

    @Override
    public void showResult(DataCity dataCity) {

        if (view!=null){

            view.showResult(dataCity);
        }
    }

    @Override
    public void getCityDetails(String nameCity, Context context) {
        if (view!=null){

            model.getCityDetails(nameCity,context);
        }
    }
}
