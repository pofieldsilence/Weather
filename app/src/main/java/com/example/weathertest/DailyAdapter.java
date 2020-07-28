package com.example.weathertest;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    ArrayList<DailyModel> dailyModelsList;

    DailyAdapter(ArrayList<DailyModel> dailyModelsList) {
        this.dailyModelsList = dailyModelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyModel dailyModel = dailyModelsList.get(position);
        ImageView icon = holder.iconDaily;
        holder.dayOfMonth.setText(dailyModel.date);

        switch (dailyModel.mainDescriptionForIcon) {
            case "Thunderstorm":
                icon.setImageResource(R.drawable.ic_thunderstorm);
                break;
            case "Drizzle":
                icon.setImageResource(R.drawable.ic_drizzle);
                break;
            case "Rain":
                icon.setImageResource(R.drawable.ic_rain);
                break;
            case "Snow":
                icon.setImageResource(R.drawable.ic_snow);
                break;
            case "Atmosphere":
                icon.setImageResource(R.drawable.ic_atmosphere);
                break;
            case "Clear":
                icon.setImageResource(R.drawable.ic_clear);
                break;
            case "Clouds":
                icon.setImageResource(R.drawable.ic_clouds);
                break;

        }


        holder.dayOfWeek.setText(dailyModel.day);
        if (dailyModel.tempDay > 0) holder.tempDay.setText("+" + dailyModel.tempDay + " °C");
        else holder.tempDay.setText(dailyModel.tempDay + " °C");
        if (dailyModel.tempNight > 0) holder.tempNight.setText("+" + dailyModel.tempNight + " °C");
        else holder.tempNight.setText(dailyModel.tempNight + " °C");
    }


    @Override
    public int getItemCount() {
        Log.d("da", "" + dailyModelsList.size());
        return dailyModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayOfMonth;
        TextView dayOfWeek;
        ImageView iconDaily;
        TextView tempDay;
        TextView tempNight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfMonth = itemView.findViewById(R.id.dayOfMonth);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            iconDaily = itemView.findViewById(R.id.iconDaily);
            tempDay = itemView.findViewById(R.id.tempDay);
            tempNight = itemView.findViewById(R.id.tempNight);


        }
    }


}
