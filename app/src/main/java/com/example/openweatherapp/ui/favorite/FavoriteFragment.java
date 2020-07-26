package com.example.openweatherapp.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.openweatherapp.databinding.FragmentFavoriteBinding;
import com.example.openweatherapp.model.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private static final String TAG = "FavoriteFragment";
    private List<CityEntity> mCityFavList;
    private Boolean isFavorite = true;

    //widgets
    private FragmentFavoriteBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private AdapterFavorites adapterCityFav;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCityFavList = new ArrayList<>();
        binding.recyclerViewFav.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCityFav = new AdapterFavorites(mCityFavList, getContext());
        binding.recyclerViewFav.setAdapter(adapterCityFav);

        adapterCityFav.setOnClickListener(view1 -> goToDetailsFav(view1));
        getListFavCities();
    }

    private void getListFavCities() {

        favoriteViewModel.getListCityFavs().observe(getViewLifecycleOwner(), cityEntities -> {
            mCityFavList = cityEntities;
            adapterCityFav.setData(mCityFavList);
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    private void goToDetailsFav(View view) {

        CityEntity cityEntity = mCityFavList.get(binding.recyclerViewFav.getChildAdapterPosition(view));
        String city = cityEntity.getNameCity()+","+cityEntity.getCountryCity();
        //Log.d(TAG,"TEXTO: " + name + ","+country);
        NavController navController = Navigation.findNavController(view);
        NavDirections action = FavoriteFragmentDirections.actionNavigationDashboardToDetailsFragment(city,isFavorite);
        navController.navigate(action);
    }
}