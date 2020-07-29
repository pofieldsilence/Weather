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
        holder.iconDaily.setImageResource(dailyModel.icon);
        holder.dayOfMonth.setText(dailyModel.date);
        holder.dayOfWeek.setText(dailyModel.day);
        holder.tempDay.setText(dailyModel.tempDay);
        holder.tempNight.setText(dailyModel.tempNight);
    }


    @Override
    public int getItemCount() {
        Log.d("da", "" + dailyModelsList.size());
        return dailyModelsList.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {

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
