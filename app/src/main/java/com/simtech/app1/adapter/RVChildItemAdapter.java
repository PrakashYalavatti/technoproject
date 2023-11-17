package com.simtech.app1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.R;
import com.simtech.app1.pojo.layout.ObservationPojo;
import com.simtech.app1.pojo.layout.PurposePojo;
import com.simtech.app1.pojo.layout.VarietyDetailsPojo;

import java.util.ArrayList;

public class RVChildItemAdapter extends RecyclerView.Adapter<RVChildItemAdapter.ChildViewHolder> {

    private final ArrayList<VarietyDetailsPojo> childItemList;
    private int nReplications, nObservationLines;
    private String parentPurpose, childItem;
//    private ArrayList<ObservationPojo> childItemList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ArrayList<PurposePojo> parentItemList;
    Context context;
    private String observation;
    private String screenFrom;

    // Constructor
    public RVChildItemAdapter(Context context, ArrayList<PurposePojo> parentItemList, String parentPurpose, ArrayList<VarietyDetailsPojo> childItemList, int nReplications, int nObservationLines, String observation) {
        this.context = context;
        this.parentPurpose = parentPurpose;
        this.parentItemList = parentItemList;
        this.childItemList = childItemList;
        this.observation = observation;
        this.nReplications = nReplications;
        this.nObservationLines = nObservationLines;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = null;
        // Here we inflate the corresponding
        // layout of the child item
        if (nReplications == 2) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_child_item, viewGroup, false);
        } else if (nReplications == 3) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_child_item, viewGroup, false);
        } else if (nReplications == 1) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_child_item, viewGroup, false);
        }
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {

        int currPos = position;
        // Create an instance of the ChildItem
        // class for the given position
        /*HashMap<String, ArrayList<VarietyDetailsPojo>> hashMap = new HashMap<>();

        for(ObservationPojo observationPojo: childItemList){
            ArrayList<VarietyDetailsPojo> varietyDetailsPojo1 = new ArrayList<>();
            for (VarietyDetailsPojo varietyDetailsPojo : observationPojo.getVarieties()) {
                VarietyDetailsPojo temp = new VarietyDetailsPojo();
                temp.setObservedvalue(varietyDetailsPojo.getObservedvalue());
                temp.setVarietycode(varietyDetailsPojo.getVarietycode());
                temp.setVarietyname(varietyDetailsPojo.getVarietyname());
                childViewHolder.child_item_title.setText(varietyDetailsPojo.getVarietycode());
                varietyDetailsPojo1.add(temp);
            }
            hashMap.put(observationPojo.getObservation(), varietyDetailsPojo1);
        }*/

        VarietyDetailsPojo childVarietyList = childItemList.get(position);
        childViewHolder.child_item_title.setText(childVarietyList.getVarietycode());

//        childViewHolder.child_item_title.setText(childVarietyList.getVarieties().get(position).getVarietycode());


        /*ChildVarietyCodeAdapter childItemAdapter = new ChildVarietyCodeAdapter(context, hashMap, nReplications, nObservationLines, observation);
        GridLayoutManager layoutManager = new GridLayoutManager(context,childItemList.size());
        childViewHolder.child_item_title.setLayoutManager(layoutManager);
        childViewHolder.child_item_title.setAdapter(childItemAdapter);
        childViewHolder.child_item_title.setRecycledViewPool(viewPool);*/
        /*ChildVarietyCodeAdapter childItemAdapter = new ChildVarietyCodeAdapter(context, childVarietyList, nReplications, nObservationLines, observation);
        GridLayoutManager layoutManager = new GridLayoutManager(context,childItemList.size());
        childViewHolder.child_item_title.setLayoutManager(layoutManager);
        childViewHolder.child_item_title.setAdapter(childItemAdapter);
        childViewHolder.child_item_title.setRecycledViewPool(viewPool);*/


        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself


//        childViewHolder.ChildItemTitle.setText(childItem.getVarieties().get(position).getVarietycode());

        /*childViewHolder.child_item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (observation.contains("Planting")) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARENT_LIST", parentItemList);
                    bundle.putString("OBSERVATION", observation);
                    bundle.putSerializable("CHILD_LIST", childItemList);
                    Intent plantation = new Intent(context, PlantationActivity.class);
                    plantation.putExtras(bundle);
                    context.startActivity(plantation);
                } else if (observation.contains("20 DAP")) {
                    UIUtils.showDialogWithL1_4(context, parentPurpose, childItemList, currPos);
                } else if (observation.contains("30 DAP")) {
                    UIUtils.showDialogWithL1_4(context, parentPurpose, childItemList, currPos);
                } else if (observation.contains("45 DAP")) {
                    UIUtils.showDialogWithL1_4(context, parentPurpose, childItemList, currPos);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {

        // This method returns the number
        // of items we have added
        // in the childItemList
        // i.e. the number of instances
        // of the childItemList
        // that have been created
        return childItemList.size();
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView child_item_title;

        ChildViewHolder(View itemView) {
            super(itemView);
            child_item_title = itemView.findViewById(R.id.child_item_title);
        }
    }
}