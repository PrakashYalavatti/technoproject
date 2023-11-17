package com.simtech.app1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.FieldLayoutActivity;
import com.simtech.app1.PlantationActivity;
import com.simtech.app1.R;
import com.simtech.app1.pojonew.TrialtypePojo;

import java.util.ArrayList;

public class MainMenuTrialObservationAdapter extends RecyclerView.Adapter<MainMenuTrialObservationAdapter.ViewHolder> {
    private ArrayList<TrialtypePojo> data;
    private Context context;
    String planningDate, locationName, locationId;



    public MainMenuTrialObservationAdapter(Context context, ArrayList<TrialtypePojo> data, String planingDate, String locationName, String locationId) {
        this.context = context;
        this.data = data;
        this.planningDate = planingDate;
        this.locationName = locationName;
        this.locationId = locationId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTrialName;
        public Button tvObservation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTrialName = itemView.findViewById(R.id.tvTrialName);
            tvObservation = itemView.findViewById(R.id.tvObservation);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrialtypePojo trialData = data.get(position);
        String observationType = trialData.getTrialstatus();
        String trialTypeId = trialData.getTrialtypeid();
        String trialTypeName = trialData.getTrialtypename();

        holder.tvTrialName.setText(trialTypeName);
        holder.tvObservation.setText(observationType);

        holder.tvObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(observationType.contains("Plan")){
                    Intent plantationIntent = new Intent(context, FieldLayoutActivity.class);
                    plantationIntent.putExtra("observationType", observationType);
                    plantationIntent.putExtra("planningDate", planningDate);
                    plantationIntent.putExtra("locationName", locationName);
                    plantationIntent.putExtra("locationId", locationId);
                    plantationIntent.putExtra("trialTypeId", trialTypeId);
                    plantationIntent.putExtra("trialTypeName", trialTypeName);
                    context.startActivity(plantationIntent);
                } else if(observationType.contains("20 DAP")){
                    Intent plantationIntent = new Intent(context, FieldLayoutActivity.class);
                    plantationIntent.putExtra("observationType", observationType);
                    plantationIntent.putExtra("planningDate", planningDate);
                    plantationIntent.putExtra("locationName", locationName);
                    plantationIntent.putExtra("locationId", locationId);
                    plantationIntent.putExtra("trialTypeId", trialTypeId);
                    plantationIntent.putExtra("trialTypeName", trialTypeName);
                    context.startActivity(plantationIntent);
                } else if(observationType.contains("30 DAP")){
                    Intent plantationIntent = new Intent(context, FieldLayoutActivity.class);
                    plantationIntent.putExtra("observationType", observationType);
                    plantationIntent.putExtra("planningDate", planningDate);
                    plantationIntent.putExtra("locationName", locationName);
                    plantationIntent.putExtra("locationId", locationId);
                    plantationIntent.putExtra("trialTypeId", trialTypeId);
                    plantationIntent.putExtra("trialTypeName", trialTypeName);
                } else if(observationType.contains("45 DAP")){
                    Intent plantationIntent = new Intent(context, FieldLayoutActivity.class);
                    plantationIntent.putExtra("observationType", observationType);
                    plantationIntent.putExtra("planningDate", planningDate);
                    plantationIntent.putExtra("locationName", locationName);
                    plantationIntent.putExtra("locationId", locationId);
                    plantationIntent.putExtra("trialTypeId", trialTypeId);
                    plantationIntent.putExtra("trialTypeName", trialTypeName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}