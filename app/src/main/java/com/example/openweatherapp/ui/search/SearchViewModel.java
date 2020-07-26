package com.example.openweatherapp.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.openweatherapp.model.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<List<String>> mCityVMList;
    private SearchRepository searchRepository;

    public SearchViewModel() {

        searchRepository = new SearchRepository();
        mCityVMList =searchRepository.getmCityMutableList();
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