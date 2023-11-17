package com.simtech.app1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.R;
import com.simtech.app1.pojo.layout.ObservationPojo;
import com.simtech.app1.pojo.layout.VarietyDetailsPojo;

import java.util.ArrayList;

public class ChildVarietyCodeAdapter extends RecyclerView.Adapter<ChildVarietyCodeAdapter.ChildViewHolder> {
    private VarietyDetailsPojo childItemList;
    //    private final ArrayList<VarietyDetailsPojo> childItemList;
    //    private HashMap<String, ArrayList<VarietyDetailsPojo>> childItemList;
    private String observation;
    private int nReplications, nObservationLines;

    private ObservationPojo childItem;
    private Context context;

    public ChildVarietyCodeAdapter(Context context, VarietyDetailsPojo childItemList, int nReplications, int nObservationLines, String observation) {
        this.context = context;
        this.childItem = childItem;
        this.childItemList = childItemList;
        this.observation = observation;
        this.nReplications = nReplications;
        this.nObservationLines = nObservationLines;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        // Here we inflate the corresponding
        // layout of the child item
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_variety_code_item, viewGroup, false);
        /*if (nReplications == 2) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_variety_code_item, viewGroup, false);
        } else if (nReplications == 3) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_variety_code_item, viewGroup, false);
        } else if (nReplications == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_variety_code_item, viewGroup, false);
        }*/
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        /*if(childItemList.containsKey("R1")){
            ArrayList<VarietyDetailsPojo> varietyDetailsPojo = childItemList.get("R1");
            holder.r1.setText(varietyDetailsPojo.get(position).getVarietycode());
        }
        if(childItemList.containsKey("R2")){
            ArrayList<VarietyDetailsPojo> varietyDetailsPojo = childItemList.get("R2");
            holder.r2.setText(varietyDetailsPojo.get(position).getVarietycode());
        }

        if(childItemList.containsKey("R3")){
            ArrayList<VarietyDetailsPojo> varietyDetailsPojo = childItemList.get("R3");
            holder.r3.setText(varietyDetailsPojo.get(position).getVarietycode());
        }*/
        holder.r1.setText(childItemList.getVarietycode());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView r1, r2, r3;

        ChildViewHolder(View itemView) {
            super(itemView);
            r1 = itemView.findViewById(R.id.r1);
            r2 = itemView.findViewById(R.id.r2);
            r3 = itemView.findViewById(R.id.r3);
        }
    }
}
