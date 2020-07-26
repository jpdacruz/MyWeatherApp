package com.example.openweatherapp.presenter;


import com.example.openweatherapp.model.DetailsInterface;
import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.model.repository.DetailsRepository;

public class DetailsPresenter implements DetailsInterface.presenter {

    private DetailsInterface.view view;
    private DetailsInterface.repository model;

    public DetailsPresenter(DetailsInterface.view view) {
        this.view = view;
        this.model = new DetailsRepository(this);
    }

    @Override
    public void showResult(CityEntity cityEntity, int codResult) {

        if (view!=null){

            view.showResult(cityEntity, codResult);
        }
    }

    @Override
    public void getCityDetails(String nameCity) {
        if (view!=null){

            model.getCityDetails(nameCity);
        }
    }
}
