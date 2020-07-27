package com.example.openweatherapp.ui.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.openweatherapp.MapBoxActivity;
import com.example.openweatherapp.databinding.FragmentDetailsBinding;
import com.example.openweatherapp.model.DetailsInterface;
import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.presenter.DetailsPresenter;
import com.example.openweatherapp.ui.favorite.FavoriteViewModel;
import com.example.openweatherapp.utils.CheckData;
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
    private FavoriteViewModel favoriteViewModel;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameCity = DetailsFragmentArgs.fromBundle(getArguments()).getCityname();

            /**
             * fragment gets bundle from Favorite Fragment
             * the value isFavorite = true if the origin is Fragment Favorite
             * the values isFavorite = false if the origin is Fragment Search
             */
            isFavorite = DetailsFragmentArgs.fromBundle(getArguments()).getIsFavorite();
            //Log.d(TAG, "IS FAVORITE: "+ isFavorite.toString());

            favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
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

        //call the presenter
        presenter = new DetailsPresenter(this);

        //clear the view
        binding.buttonToMaps.setVisibility(View.GONE);
        binding.progressBarDetails.setVisibility(View.VISIBLE);
        binding.imageButtonFav.setVisibility(View.GONE);


        binding.buttonToMaps.setOnClickListener(view1 -> {
            toActivityMap();
        });

        binding.imageButtonFav.setOnClickListener(view12 -> {
            insertCityRoom();
        });

        //get details for selected city
        presenter.getCityDetails(nameCity);
    }

    private void insertCityRoom() {

        favoriteViewModel.getListCityFavs().observe(getViewLifecycleOwner(), new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(List<CityEntity> cityEntities) {

                String idCityToInsert = cityEntity.getIdCity();

                if (CheckData.isCityExist(cityEntities, idCityToInsert)) {

                    Toast.makeText(getContext(), "Esta ciudad ya está en favoritas", Toast.LENGTH_SHORT).show();

                } else {

                    binding.imageButtonFav.setVisibility(View.GONE);
                    favoriteViewModel.insertCityFav(cityEntity);
                }
                //Toast.makeText(getContext(), cityEntity.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toActivityMap() {

        Intent intent = new Intent(getActivity(), MapBoxActivity.class);
        /**
         *set bundle extra.
         *Send to ActivityMap the Entity city, to set the long and Lat in the map
         */
        intent.putExtra("city", cityEntity);
        startActivity(intent);
    }

    @Override
    public void showResult(CityEntity mCityEntity, int result) {

        /**
         * int result for test purposes
         */

        cityEntity = mCityEntity;

        binding.progressBarDetails.setVisibility(View.GONE);
        binding.textViewCityName.setText(String.format("%s ,%s", cityEntity.getNameCity(), cityEntity.getCountryCity()));
        binding.textViewCent.setText("ºC");

        //show weather icon
        String icon = cityEntity.getWeatherIconCity();
        String urlImage = SetImageUrl.setImageUrl(icon);
        //Log.d(TAG, "URL: "  + urlImage);
        Glide.with(getContext()).load(urlImage).into(binding.imageView);

        binding.textViewDescription.setText(cityEntity.getWeatherDescriptionCity());
        binding.textViewTemp.setText(cityEntity.getTempCity());
        binding.textViewPressure.setText(String.format("Presión Atmosférica: %s", cityEntity.getPressureCity()));
        binding.textViewHumidity.setText(String.format("Humedad: %s", cityEntity.getHumidityCity()));
        binding.textViewmaxTemp.setText(String.format("Máxima: %s", cityEntity.getMaxTempCity()));
        binding.textViewMinTemp.setText(String.format("Minima: %s", cityEntity.getMinTempCity()));

        /**
         * if isFavorite:
         * show colored addFavorite button
         * disable button (cant click on it)
         */
        if (isFavorite) {
            binding.imageButtonFav.setImageResource(android.R.drawable.btn_star_big_on);
            binding.imageButtonFav.setClickable(false);
        }

        //show buttons in the view after the city data is displayed
        binding.imageButtonFav.setVisibility(View.VISIBLE);
        binding.buttonToMaps.setVisibility(View.VISIBLE);
    }
}