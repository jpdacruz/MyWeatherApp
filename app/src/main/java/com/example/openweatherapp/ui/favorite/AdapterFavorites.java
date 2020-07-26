package com.example.openweatherapp.ui.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweatherapp.R;
import com.example.openweatherapp.model.entity.CityEntity;

import java.util.List;

public class AdapterFavorites
        extends RecyclerView.Adapter<AdapterFavorites.ViewHolder>
        implements View.OnClickListener {

    private List<CityEntity> mCitiesFavList;
    private Context context;
    private View.OnClickListener listener;

    public AdapterFavorites(List<CityEntity> mCitiesFavList, Context context) {
        this.mCitiesFavList = mCitiesFavList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cities, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CityEntity cityEntity = mCitiesFavList.get(position);
        holder.tvCityName.setText(String.format("%s, %s", cityEntity.getNameCity(), cityEntity.getCountryCity()));
    }

    @Override
    public int getItemCount() {
        if (mCitiesFavList != null) {
            return mCitiesFavList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onClick(View view) {
        if(listener != null){

            listener.onClick(view);
        }
    }

    public void setData(List<CityEntity> cityNames) {

        this.mCitiesFavList = cityNames;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCityName = itemView.findViewById(R.id.textViewItemListCities);
        }
    }
}
