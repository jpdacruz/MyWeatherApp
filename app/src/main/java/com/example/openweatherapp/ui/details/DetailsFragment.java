package com.example.openweatherapp.ui.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.openweatherapp.MapsActivity;
import com.example.openweatherapp.clases.Constantes;
import com.example.openweatherapp.clases.DataCity;
import com.example.openweatherapp.databinding.FragmentDetailsBinding;
import com.example.openweatherapp.interfaces.DetailsInterface;
import com.example.openweatherapp.presenter.DetailsPresenter;

public class DetailsFragment extends Fragment implements DetailsInterface.view {

    private static final String TAG = "DetailsFragment";
    FragmentDetailsBinding binding;
    private String nameCity;
    private DetailsInterface.presenter presenter;
    private DataCity city;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameCity = DetailsFragmentArgs.fromBundle(getArguments()).getCityname();
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

        city = new DataCity();
        presenter = new DetailsPresenter(this);
        binding.buttonToMaps.setVisibility(View.GONE);
        binding.buttonToMaps.setOnClickListener(view1 -> {
            toActivityMap();
        });
        binding.progressBarDetails.setVisibility(View.VISIBLE);
        presenter.getCityDetails(nameCity,getContext());
    }

    private void toActivityMap() {

        if(city != null){
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("city",city);
            startActivity(intent);
        }
    }

    @Override
    public void showResult(DataCity dataCity) {

        String urlImage = new StringBuilder(Constantes.BASE_URL_IMAGES)
                .append(dataCity.getWeatherIconCity())
                .append("@2x")
                .append(".png").toString();

        binding.progressBarDetails.setVisibility(View.GONE);
        binding.textViewCityName.setText(String.format("%s ,%s", dataCity.getNameCity(), dataCity.getCountryCity()));
        binding.textViewCent.setText("ºC");

        Glide.with(getContext()).load(urlImage).into(binding.imageView);

        binding.textViewDescription.setText(dataCity.getWeatherDescriptionCity());
        binding.textViewTemp.setText(dataCity.getTempCity());
        binding.textViewPressure.setText(String.format("Presión Atmosférica: %s", dataCity.getPressureCity()));
        binding.textViewHumidity.setText(String.format("Humedad: %s", dataCity.getHumidityCity()));
        binding.textViewmaxTemp.setText(String.format("Máxima: %s", dataCity.getMaxTempCity()));
        binding.textViewMinTemp.setText(String.format("Minima: %s", dataCity.getMinTempCity()));

        city = dataCity;
        binding.buttonToMaps.setVisibility(View.VISIBLE);

        /**
         * to test MVP
        Toast.makeText(getContext(), nameCity, Toast.LENGTH_SHORT).show();
         */
    }
}