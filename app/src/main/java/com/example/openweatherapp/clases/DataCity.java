package com.example.openweatherapp.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class DataCity implements Parcelable {

    private String nameCity;
    private String countryCity;
    private String tempCity;
    private String pressureCity;
    private String humidityCity;
    private String maxTempCity;
    private String minTempCity;
    private String latCoordCity;
    private String longCoordCity;
    private String weatherIdCity;
    private String weatherConditionCity;
    private String weatherDescriptionCity;
    private String weatherIconCity;

    public DataCity(String nameCity, String countryCity, String tempCity, String pressureCity, String humidityCity, String maxTempCity, String minTempCity, String latCoordCity, String longCoordCity, String weatherIdCity, String weatherConditionCity, String weatherDescriptionCity, String weatherIconCity) {
        this.nameCity = nameCity;
        this.countryCity = countryCity;
        this.tempCity = tempCity;
        this.pressureCity = pressureCity;
        this.humidityCity = humidityCity;
        this.maxTempCity = maxTempCity;
        this.minTempCity = minTempCity;
        this.latCoordCity = latCoordCity;
        this.longCoordCity = longCoordCity;
        this.weatherIdCity = weatherIdCity;
        this.weatherConditionCity = weatherConditionCity;
        this.weatherDescriptionCity = weatherDescriptionCity;
        this.weatherIconCity = weatherIconCity;
    }

    public DataCity() {
    }

    protected DataCity(Parcel in) {
        nameCity = in.readString();
        countryCity = in.readString();
        tempCity = in.readString();
        pressureCity = in.readString();
        humidityCity = in.readString();
        maxTempCity = in.readString();
        minTempCity = in.readString();
        latCoordCity = in.readString();
        longCoordCity = in.readString();
        weatherIdCity = in.readString();
        weatherConditionCity = in.readString();
        weatherDescriptionCity = in.readString();
        weatherIconCity = in.readString();
    }

    public static final Creator<DataCity> CREATOR = new Creator<DataCity>() {
        @Override
        public DataCity createFromParcel(Parcel in) {
            return new DataCity(in);
        }

        @Override
        public DataCity[] newArray(int size) {
            return new DataCity[size];
        }
    };

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getCountryCity() {
        return countryCity;
    }

    public void setCountryCity(String countryCity) {
        this.countryCity = countryCity;
    }

    public String getTempCity() {
        return tempCity;
    }

    public void setTempCity(String tempCity) {
        this.tempCity = tempCity;
    }

    public String getPressureCity() {
        return pressureCity;
    }

    public void setPressureCity(String pressureCity) {
        this.pressureCity = pressureCity;
    }

    public String getHumidityCity() {
        return humidityCity;
    }

    public void setHumidityCity(String humidityCity) {
        this.humidityCity = humidityCity;
    }

    public String getMaxTempCity() {
        return maxTempCity;
    }

    public void setMaxTempCity(String maxTempCity) {
        this.maxTempCity = maxTempCity;
    }

    public String getMinTempCity() {
        return minTempCity;
    }

    public void setMinTempCity(String minTempCity) {
        this.minTempCity = minTempCity;
    }

    public String getLatCoordCity() {
        return latCoordCity;
    }

    public void setLatCoordCity(String latCoordCity) {
        this.latCoordCity = latCoordCity;
    }

    public String getLongCoordCity() {
        return longCoordCity;
    }

    public void setLongCoordCity(String longCoordCity) {
        this.longCoordCity = longCoordCity;
    }

    public String getWeatherIdCity() {
        return weatherIdCity;
    }

    public void setWeatherIdCity(String weatherIdCity) {
        this.weatherIdCity = weatherIdCity;
    }

    public String getWeatherConditionCity() {
        return weatherConditionCity;
    }

    public void setWeatherConditionCity(String weatherConditionCity) {
        this.weatherConditionCity = weatherConditionCity;
    }

    public String getWeatherDescriptionCity() {
        return weatherDescriptionCity;
    }

    public void setWeatherDescriptionCity(String weatherDescriptionCity) {
        this.weatherDescriptionCity = weatherDescriptionCity;
    }

    public String getWeatherIconCity() {
        return weatherIconCity;
    }

    public void setWeatherIconCity(String weatherIconCity) {
        this.weatherIconCity = weatherIconCity;
    }

    @Override
    public String toString() {
        return "DataCity{" +
                "nameCity='" + nameCity + '\'' +
                ", countryCity='" + countryCity + '\'' +
                ", tempCity='" + tempCity + '\'' +
                ", pressureCity='" + pressureCity + '\'' +
                ", humidityCity='" + humidityCity + '\'' +
                ", maxTempCity='" + maxTempCity + '\'' +
                ", minTempCity='" + minTempCity + '\'' +
                ", latCoordCity='" + latCoordCity + '\'' +
                ", longCoordCity='" + longCoordCity + '\'' +
                ", weatherIdCity='" + weatherIdCity + '\'' +
                ", weatherConditionCity='" + weatherConditionCity + '\'' +
                ", weatherDescriptionCity='" + weatherDescriptionCity + '\'' +
                ", weatherIconCity='" + weatherIconCity + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameCity);
        parcel.writeString(countryCity);
        parcel.writeString(tempCity);
        parcel.writeString(pressureCity);
        parcel.writeString(humidityCity);
        parcel.writeString(maxTempCity);
        parcel.writeString(minTempCity);
        parcel.writeString(latCoordCity);
        parcel.writeString(longCoordCity);
        parcel.writeString(weatherIdCity);
        parcel.writeString(weatherConditionCity);
        parcel.writeString(weatherDescriptionCity);
        parcel.writeString(weatherIconCity);
    }
}
