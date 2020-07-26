package com.example.openweatherapp.utils;

public class SetImageUrl {

    public static String setImageUrl(String weatherIconCity){

        String urlImage = new StringBuilder(Constantes.BASE_URL_IMAGES)
                .append(weatherIconCity)
                .append("@2x")
                .append(".png").toString();
        return urlImage;
    }
}
