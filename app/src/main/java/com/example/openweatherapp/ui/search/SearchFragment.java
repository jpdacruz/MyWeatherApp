package com.example.openweatherapp.ui.search;

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

import com.example.openweatherapp.databinding.FragmentSearchBinding;
import com.example.openweatherapp.model.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = "ListCitiesFragment";

    //vars
    private List<String> mCityList;
    private List<String> mCityFoundList;
    private CityEntity cityEntity;
    private Boolean isFavorite = false;

    //widgets
    private SearchViewModel searchViewModel;
    private AdapterSearch adapterSearch;
    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);

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
        binding.recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSearch = new AdapterSearch(mCityFoundList, getContext());
        binding.recyclerViewCities.setAdapter(adapterSearch);
        adapterSearch.setOnClickListener(view1 -> goToDetails(view1));

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
                mCityFoundList = searchViewModel.setCityFoundVMList(mCityList,s);
                adapterSearch.setData(mCityFoundList);
                return true;
            }
        });
    }

    private void getListCitiesViewModel() {

        searchViewModel.getmCityVMList().observe(getViewLifecycleOwner(), cities -> {
            mCityList = cities;
            binding.progressBarCities.setVisibility(View.GONE);
        });
    }

    private void goToDetails(View view) {

        String city = mCityFoundList.get(binding.recyclerViewCities.getChildAdapterPosition(view));
        binding.searchViewCities.setQuery("", false);
        binding.searchViewCities.clearFocus();
        NavController navController = Navigation.findNavController(view);
        NavDirections action = SearchFragmentDirections.actionNavigationNotificationsToDetailsFragment(city,isFavorite);
        navController.navigate(action);
    }
}