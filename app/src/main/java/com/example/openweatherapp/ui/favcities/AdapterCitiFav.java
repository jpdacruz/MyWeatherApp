package com.example.openweatherapp.ui.favcities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweatherapp.R;
import com.example.openweatherapp.room.CityEntity;

import java.util.List;

public class AdapterCitiFav
        extends RecyclerView.Adapter<AdapterCitiFav.ViewHolder>
        implements View.OnClickListener {

    private List<CityEntity> mCitiesFavsList;
    private Context context;
    private View.OnClickListener listener;

    public AdapterCitiFav(List<CityEntity> mCitiesFavsList, Context context) {
        this.mCitiesFavsList = mCitiesFavsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fav_city, parent, false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CityEntity cityEntity = mCitiesFavsList.get(position);
        holder.tvCityName.setText(cityEntity.getNameCity());
        holder.tvCountryName.setText(cityEntity.getCountryCity());
    }

    @Override
    public int getItemCount() {

        if (mCitiesFavsList != null) {
            return mCitiesFavsList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<CityEntity> cityNames) {

        this.mCitiesFavsList = cityNames;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

        if(listener != null){

            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCityName;
        private TextView tvCountryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCityName = itemView.findViewById(R.id.textViewFavCity);
            tvCountryName = itemView.findViewById(R.id.textViewFavCountry);
        }
    }
}
