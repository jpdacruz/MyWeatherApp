package com.example.openweatherapp.ui.listcities;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.openweatherapp.repository.ListCityRepository;

import java.util.ArrayList;
import java.util.List;

public class ListCitiesViewModel extends ViewModel {

    private MutableLiveData<List<String>> mCityVMList;
    private ListCityRepository repositorio;

    public ListCitiesViewModel() {

        repositorio = new ListCityRepository();
        mCityVMList = repositorio.getmCityMutableList();
    }

    public MutableLiveData<List<String>> getmCityVMList() {
        return mCityVMList;
    }

    public List<String> setCityFoundVMList(List<String> mCityList, String s){

        List<String> mCityFoundList = new ArrayList<>();

        for (String cityFound : mCityList){
            if(cityFound.toLowerCase().contains(s.toLowerCase())){
                mCityFoundList.add(cityFound);
            }
        }
        return mCityFoundList;
    }
}