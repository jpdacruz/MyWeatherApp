package com.example.openweatherapp.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.openweatherapp.utils.Constantes;
import com.example.openweatherapp.utils.DataCity;
import com.example.openweatherapp.interfaces.DetailsInterface;
import com.example.openweatherapp.utils.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsModel implements DetailsInterface.model {

    private static final String TAG = "DetailsModel";
    private DetailsInterface.presenter presenter;

    private DataCity dataCity;

    public DetailsModel(DetailsInterface.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getCityDetails(String nameCity, Context context) {

        final String URL = new StringBuilder(Constantes.BASE_URL_CITY)
                .append("?q=")
                .append(nameCity)
                .append("&lang=")
                .append(Constantes.LANG)
                .append("&units=metric&appId=")
                .append(Constantes.APIKEY)
                .toString();
        //String url2 = "https://api.openweathermap.org/data/2.5/weather?q="+nameCity+"&lang=sp&units=metric&appId=0896430de0f30dab7ba1b9c5eb39bdcd";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {

            dataCity = new DataCity();
            try {
                JSONObject weatherJsonObject = new JSONObject(response);
                dataCity.setNameCity(weatherJsonObject.getString("name"));
                dataCity.setCountryCity(weatherJsonObject.getJSONObject("sys").getString("country"));
                dataCity.setTempCity(weatherJsonObject.getJSONObject("main").getString("temp"));
                dataCity.setPressureCity(weatherJsonObject.getJSONObject("main").getString("pressure"));
                dataCity.setHumidityCity(weatherJsonObject.getJSONObject("main").getString("humidity"));
                dataCity.setMaxTempCity(weatherJsonObject.getJSONObject("main").getString("temp_max"));
                dataCity.setMinTempCity(weatherJsonObject.getJSONObject("main").getString("temp_min"));
                dataCity.setLongCoordCity(weatherJsonObject.getJSONObject("coord").getString("lon"));
                dataCity.setLatCoordCity(weatherJsonObject.getJSONObject("coord").getString("lat"));

                JSONArray jsonArray = weatherJsonObject.getJSONArray("weather");
                JSONObject weatherInfoJsonObject = jsonArray.getJSONObject(0);
                dataCity.setWeatherIdCity(weatherInfoJsonObject.getString("id"));
                dataCity.setWeatherDescriptionCity(weatherInfoJsonObject.getString("description"));
                dataCity.setWeatherIconCity(weatherInfoJsonObject.getString("icon"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            presenter.showResult(dataCity);
        }, error -> Log.d(TAG, error.toString()));

        RequestSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
