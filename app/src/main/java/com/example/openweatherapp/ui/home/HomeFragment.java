package com.example.openweatherapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.openweatherapp.databinding.FragmentHomeBinding;
import com.example.openweatherapp.model.DetailsInterface;
import com.example.openweatherapp.model.entity.CityEntity;
import com.example.openweatherapp.presenter.DetailsPresenter;
import com.example.openweatherapp.utils.SetImageUrl;

public class HomeFragment extends Fragment implements DetailsInterface.view {

    private static final String TAG = "HomeFragment";
    FragmentHomeBinding binding;
    private DetailsInterface.presenter presenter;
    //private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
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

        //
        presenter = new DetailsPresenter(this);
        presenter.getCityDetails("Ciudad Autónoma de Buenos Aires,AR");

    }

    @Override
    public void showResult(CityEntity cityEntity, int codResult) {
        binding.progressBarDetails.setVisibility(View.GONE);

        binding.textViewCityName.setText(String.format("%s ,%s", cityEntity.getNameCity(), cityEntity.getCountryCity()));
        binding.textViewCent.setText("ºC");

        String icon = cityEntity.getWeatherIconCity();
        //use Util.SetImageUrl method to get the complete URL
        String urlImage = SetImageUrl.setImageUrl(icon);
        Glide.with(getContext()).load(urlImage).into(binding.imageView);

        binding.textViewDescription.setText(cityEntity.getWeatherDescriptionCity());
        binding.textViewTemp.setText(cityEntity.getTempCity());
        binding.textViewPressure.setText(String.format("Presión Atmosférica: %s", cityEntity.getPressureCity()));
        binding.textViewHumidity.setText(String.format("Humedad: %s", cityEntity.getHumidityCity()));
        binding.textViewmaxTemp.setText(String.format("Máxima: %s", cityEntity.getMaxTempCity()));
        binding.textViewMinTemp.setText(String.format("Minima: %s", cityEntity.getMinTempCity()));
    }
}