package com.example.openweatherapp.ui.favcities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.openweatherapp.R;
import com.example.openweatherapp.databinding.FragmentFavoriteBinding;
import com.example.openweatherapp.room.CityEntity;
import com.example.openweatherapp.utils.DataCity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private static final String TAG = "FavoriteFragment";
    private List<CityEntity> mCityFavList;
    private AdapterCitiFav adapterCitiFav;
    private FragmentFavoriteBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private DataCity dataCity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        binding = FragmentFavoriteBinding.inflate(inflater,container,false);

        View view  = binding.getRoot();
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

        mCityFavList = new ArrayList<>();
        binding.progressBarFavCities.setVisibility(View.GONE);
        binding.recyclerviewFavCities.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCitiFav = new AdapterCitiFav(mCityFavList,getContext());
        binding.recyclerviewFavCities.setAdapter(adapterCitiFav);
        adapterCitiFav.setOnClickListener(view1 -> {goToDetails(view);});

        getListFavCitiesViewModel();
    }

    private void getListFavCitiesViewModel() {

        favoriteViewModel.getListCityFavs().observe(getActivity(), new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(List<CityEntity> cityEntities) {

                mCityFavList = cityEntities;
                adapterCitiFav.setData(mCityFavList);
            }
        });
    }

    private void goToDetails(View view) {
        
    }
}