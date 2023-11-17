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

public class RVParentItemAdapter extends RecyclerView.Adapter<RVParentItemAdapter.ParentViewHolder> {

    private Context context;
    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ArrayList<PurposePojo> parentItemList;
    private String screenFrom;
    int nReplications, nObservationLines;

    public RVParentItemAdapter(Context context, ArrayList<PurposePojo> parentItemList, String screenFrom, String nReplications, String nObservationLines)
    {
        this.context = context;
        this.parentItemList = parentItemList;
        this.screenFrom = screenFrom;
        this.nReplications = Integer.parseInt(nReplications);
        this.nObservationLines = Integer.parseInt(nObservationLines);
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the parent item
        View view = null;
        if(nReplications == 2){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_parent_layout_2,viewGroup, false);
        } else if(nReplications == 3){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_parent_layout_3,viewGroup, false);
        } else if(nReplications == 1){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_parent_layout_1,viewGroup, false);
        }

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position)
    {

        // Create an instance of the ParentItem
        // class for the given position
        PurposePojo parentItem = parentItemList.get(position);

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        String purposeTitle = parentItem.getPurpose();
        parentViewHolder.ParentItemTitle.setText(purposeTitle);

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false);*/

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
        /*layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());*/

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        RVChildItemAdapter childItemAdapter = new RVChildItemAdapter(context, parentItemList, purposeTitle, parentItem.getObservations().get(0).getVarieties(), nReplications, nObservationLines, screenFrom);
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    @Override
    public int getItemCount()
    {

        return parentItemList.size();
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView)
        {
            super(itemView);

            ParentItemTitle = itemView.findViewById( R.id.parent_item_title);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}