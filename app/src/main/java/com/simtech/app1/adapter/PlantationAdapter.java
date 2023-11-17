package com.simtech.app1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.R;
import com.simtech.app1.pojo.RVChildItem;
import com.simtech.app1.pojo.RVParentItem;
import com.simtech.app1.pojo.layout.ObservationPojo;
import com.simtech.app1.pojo.layout.PurposePojo;

import java.util.ArrayList;

public class PlantationAdapter extends RecyclerView.Adapter<PlantationAdapter.ViewHolder>{

    private ArrayList<PurposePojo> parentItemList;
    private ArrayList<ObservationPojo> childItemList;
    private Context context;
    public PlantationAdapter(ArrayList<PurposePojo> parentItemList, ArrayList<ObservationPojo> childItemList, String observation, Context context) {
        this.context = context;
        this.parentItemList = parentItemList;
        this.childItemList = childItemList;
    }

    @NonNull
    @Override
    public PlantationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantation_item, parent, false);
        return new PlantationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantationAdapter.ViewHolder holder, int position) {
        ObservationPojo childItem = childItemList.get(position);

        holder.tvVerityCode.setText(childItem.getVarieties().get(position).getVarietycode());
        holder.tvVerityName.setText(childItem.getVarieties().get(position).getVarietyname());
        holder.tvPurpose.setText(parentItemList.get(position).getPurpose());
    }

    @Override
    public int getItemCount() {
        return childItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVerityCode, tvVerityName, tvPurpose;
        EditText etSample1, etSample2, etSample3;

        RecyclerView rvTrialObservation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvVerityCode = itemView.findViewById(R.id.tvVerityCode);
            tvVerityName = itemView.findViewById(R.id.tvVerityName);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
        }
    }
}
