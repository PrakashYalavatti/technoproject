package com.simtech.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.adapter.MainMenuAdapter;
import com.simtech.app1.adapter.PlantationAdapter;
import com.simtech.app1.pojo.RVChildItem;
import com.simtech.app1.pojo.RVParentItem;
import com.simtech.app1.pojo.layout.ObservationPojo;
import com.simtech.app1.pojo.layout.PurposePojo;

import java.util.ArrayList;

public class PlantationActivity extends AppCompatActivity {
    private String observation;
    private ArrayList<PurposePojo> parentItemList;

    private ArrayList<ObservationPojo> childItemList;
    private RecyclerView rvPlantation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantation_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.at_planting));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashScreen = new Intent(PlantationActivity.this, MainMenuActivity.class);
                startActivity(splashScreen);
            }
        });


        Bundle bundle = getIntent().getExtras();
        observation = getIntent().getStringExtra("observation");
        parentItemList = (ArrayList<PurposePojo>) bundle.getSerializable("PARENT_LIST");
        childItemList = (ArrayList<ObservationPojo>) bundle.getSerializable("CHILD_LIST");
        bundle.getString("OBSERVATION");


        rvPlantation = findViewById(R.id.rvPlantation);

        PlantationAdapter plantationAdapter = new PlantationAdapter(parentItemList, childItemList, observation, PlantationActivity.this);
        rvPlantation.setAdapter(plantationAdapter);
    }
}
