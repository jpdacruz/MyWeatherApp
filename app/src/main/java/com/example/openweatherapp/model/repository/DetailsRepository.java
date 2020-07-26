package com.example.openweatherapp.model.repository;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.openweatherapp.model.DetailsInterface;
import com.example.openweatherapp.model.RequestVolleySingleton;
import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.utils.Constantes;
import com.example.openweatherapp.utils.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsRepository implements DetailsInterface.repository {

    private static final String TAG = "DetailsModel";
    private DetailsInterface.presenter presenter;

    private CityEntity cityEntity;

    public DetailsRepository(DetailsInterface.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getCityDetails(String nameCity) {

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

            cityEntity = new CityEntity();
            try {
                JSONObject weatherJsonObject = new JSONObject(response);
                cityEntity.setIdCity(weatherJsonObject.getString("id"));
                //Log.d(TAG,cityEntity.getIdCity());
                cityEntity.setNameCity(weatherJsonObject.getString("name"));
                cityEntity.setCountryCity(weatherJsonObject.getJSONObject("sys").getString("country"));
                cityEntity.setTempCity(weatherJsonObject.getJSONObject("main").getString("temp"));
                cityEntity.setPressureCity(weatherJsonObject.getJSONObject("main").getString("pressure"));
                cityEntity.setHumidityCity(weatherJsonObject.getJSONObject("main").getString("humidity"));
                cityEntity.setMaxTempCity(weatherJsonObject.getJSONObject("main").getString("temp_max"));
                cityEntity.setMinTempCity(weatherJsonObject.getJSONObject("main").getString("temp_min"));
                cityEntity.setLongCoordCity(weatherJsonObject.getJSONObject("coord").getString("lon"));
                cityEntity.setLatCoordCity(weatherJsonObject.getJSONObject("coord").getString("lat"));

                JSONArray jsonArray = weatherJsonObject.getJSONArray("weather");
                JSONObject weatherInfoJsonObject = jsonArray.getJSONObject(0);
                cityEntity.setWeatherIdCity(weatherInfoJsonObject.getString("id"));
                cityEntity.setWeatherDescriptionCity(weatherInfoJsonObject.getString("description"));
                cityEntity.setWeatherIconCity(weatherInfoJsonObject.getString("icon"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            presenter.showResult(cityEntity, 0);
        }, error -> Log.d(TAG, error.toString()));

        RequestVolleySingleton.getInstance(MyApp.getContext()).addToRequestQueue(stringRequest);
    }
}
