package com.example.openweatherapp.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.openweatherapp.R;
import com.example.openweatherapp.databinding.FragmentDetailsBinding;
import com.example.openweatherapp.model.DetailsInterface;
import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.presenter.DetailsPresenter;
import com.example.openweatherapp.ui.favorite.FavoriteViewModel;
import com.example.openweatherapp.utils.Constantes;
import com.example.openweatherapp.utils.SetImageUrl;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment implements DetailsInterface.view {

    private static final String TAG = "DetailsFragment";
    //vars
    private String nameCity;
    private Boolean isFavorite;
    private List<String> mCityList;
    private List<String> mCityFoundList;
    private CityEntity cityEntity;
    private DetailsInterface.presenter presenter;

    //widgets
    private FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameCity = DetailsFragmentArgs.fromBundle(getArguments()).getCityname();
            isFavorite = DetailsFragmentArgs.fromBundle(getArguments()).getIsFavorite();

            //Log.d(TAG, "IS FAVORITE: "+ isFavorite.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCityList = new ArrayList<>();
        mCityFoundList = new ArrayList<>();
        cityEntity = new CityEntity();
        presenter = new DetailsPresenter(this);

        binding.buttonToMaps.setVisibility(View.GONE);
        binding.progressBarDetails.setVisibility(View.VISIBLE);
        binding.imageButtonFav.setVisibility(View.GONE);
        binding.buttonToMaps.setOnClickListener(view1 -> {
            toActivityMap();
        });

        binding.imageButtonFav.setOnClickListener(view12 -> {
            insertCityRoom();
        });

        presenter.getCityDetails(nameCity);
    }

    private void insertCityRoom() {

        if(cityEntity!=null){

            FavoriteViewModel favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

            try {

                binding.imageButtonFav.setVisibility(View.GONE);
                favoriteViewModel.insertCityFav(cityEntity);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //Toast.makeText(getContext(), cityEntity.toString(), Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(getContext(), "NO SE PUEDE INSERTAR", Toast.LENGTH_SHORT).show();
        }
    }

    private void toActivityMap() {

    }

    @Override
    public void showResult(CityEntity mCityEntity, int result) {

        cityEntity = mCityEntity;
        String icon = cityEntity.getWeatherIconCity();
        String urlImage = SetImageUrl.setImageUrl(icon);
        //Log.d(TAG, "URL: "  + urlImage);
        binding.progressBarDetails.setVisibility(View.GONE);
        binding.textViewCityName.setText(String.format("%s ,%s", cityEntity.getNameCity(), cityEntity.getCountryCity()));
        binding.textViewCent.setText("ºC");

        Glide.with(getContext()).load(urlImage).into(binding.imageView);

        binding.textViewDescription.setText(cityEntity.getWeatherDescriptionCity());
        binding.textViewTemp.setText(cityEntity.getTempCity());
        binding.textViewPressure.setText(String.format("Presión Atmosférica: %s", cityEntity.getPressureCity()));
        binding.textViewHumidity.setText(String.format("Humedad: %s", cityEntity.getHumidityCity()));
        binding.textViewmaxTemp.setText(String.format("Máxima: %s", cityEntity.getMaxTempCity()));
        binding.textViewMinTemp.setText(String.format("Minima: %s", cityEntity.getMinTempCity()));

        if (isFavorite){
            showFavButtonColored();
        }
        binding.imageButtonFav.setVisibility(View.VISIBLE);
        binding.buttonToMaps.setVisibility(View.VISIBLE);
    }

    private void showFavButtonColored() {
        binding.imageButtonFav.setImageResource(android.R.drawable.btn_star_big_on);
        binding.imageButtonFav.setClickable(false);
    }
}