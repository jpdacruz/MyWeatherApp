package com.example.openweatherapp.ui.listcities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.openweatherapp.databinding.FragmentListcitiesBinding;

import java.util.ArrayList;
import java.util.List;

public class ListCitiesFragment extends Fragment {

    private static final String TAG = "ListCitiesFragment";
    private ListCitiesViewModel listCitiesViewModel;
    private List<String> mCityList;
    private List<String> mCityFoundList;
    private AdapterListCity adapterListCity;
    private FragmentListcitiesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listCitiesViewModel =
                ViewModelProviders.of(this).get(ListCitiesViewModel.class);
        binding = FragmentListcitiesBinding.inflate(inflater, container, false);

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
        binding.recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterListCity = new AdapterListCity(mCityFoundList, getContext());
        binding.recyclerViewCities.setAdapter(adapterListCity);
        adapterListCity.setOnClickListener(view1 -> goToDetails(view1));

        getListCitiesViewModel();
        initSearchView();
    }

    private void initSearchView() {

        binding.searchViewCities.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mCityFoundList = listCitiesViewModel.setCityFoundVMList(mCityList,s);
                adapterListCity.setData(mCityFoundList);
                return true;
            }
        });
    }

    private void getListCitiesViewModel() {

        listCitiesViewModel.getmCityVMList().observe(getViewLifecycleOwner(), cities -> {
            mCityList = cities;
            binding.progressBarCities.setVisibility(View.GONE);
        });
    }

    private void goToDetails(View view) {

        String city = mCityFoundList.get(binding.recyclerViewCities.getChildAdapterPosition(view));
        binding.searchViewCities.setQuery("", false);
        binding.searchViewCities.clearFocus();
        NavController navController = Navigation.findNavController(view);
        NavDirections action = ListCitiesFragmentDirections.actionNavigationNotificationsToDetailsFragment(city);
        navController.navigate(action);
    }
}