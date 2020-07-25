package com.example.openweatherapp.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "cities")
public class CityEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nameCity;
    public String countryCity;

    public CityEntity() {
    }

    public CityEntity(String nameCity, String countryCity) {
        this.nameCity = nameCity;
        this.countryCity = countryCity;
    }

    protected CityEntity(Parcel in) {
        id = in.readInt();
        nameCity = in.readString();
        countryCity = in.readString();
    }

    public static final Creator<CityEntity> CREATOR = new Creator<CityEntity>() {
        @Override
        public CityEntity createFromParcel(Parcel in) {
            return new CityEntity(in);
        }

        @Override
        public CityEntity[] newArray(int size) {
            return new CityEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", nameCity='" + nameCity + '\'' +
                ", countryCity='" + countryCity + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nameCity);
        parcel.writeString(countryCity);
    }
}
