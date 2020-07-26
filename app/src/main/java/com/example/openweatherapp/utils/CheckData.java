package com.example.openweatherapp.utils;

import android.view.View;
import android.widget.Toast;

import com.example.openweatherapp.model.entity.CityEntity;

import java.util.List;

public class CheckData {

    public static boolean isCityExist(List<CityEntity> cityEntities, String idCity) {

        for (CityEntity c : cityEntities) {
            if (c.getIdCity().contains(idCity)) {
                return true;
            }
        }
        return false;
    }
}
