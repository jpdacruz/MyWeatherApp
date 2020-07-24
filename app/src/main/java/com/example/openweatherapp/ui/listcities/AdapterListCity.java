package com.example.openweatherapp.ui.listcities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweatherapp.R;

import java.util.List;

public class AdapterListCity
        extends RecyclerView.Adapter<AdapterListCity.ViewHolder>
        implements View.OnClickListener {

    private List<String> mCityList;
    private Context context;
    private View.OnClickListener listener;

    public AdapterListCity(List<String> mCityList, Context context) {
        this.mCityList = mCityList;
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

        String cityName = mCityList.get(position);
        holder.tvCityName.setText(cityName);
    }

    @Override
    public int getItemCount() {
        if (mCityList != null) {
            return mCityList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<String> cityNames) {

        this.mCityList = cityNames;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCityName = itemView.findViewById(R.id.textViewItemListCities);
        }
    }
}
