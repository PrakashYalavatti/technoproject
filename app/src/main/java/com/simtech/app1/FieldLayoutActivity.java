package com.simtech.app1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.simtech.app1.apiservices.APIClient;
import com.simtech.app1.apiservices.APIInterface;
import com.simtech.app1.apputils.UIUtils;
import com.simtech.app1.pojo.RVChildItem;
import com.simtech.app1.pojo.RVParentItem;
import com.simtech.app1.adapter.RVParentItemAdapter;
import com.simtech.app1.pojo.layout.LayoutDetailsPojo;
import com.simtech.app1.pojo.layout.LocationDetailsDataPojo;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FieldLayoutActivity extends AppCompatActivity {
    private String observation;
    private APIInterface apiInterface;
    private SharedPreferences mCredentialsStorage;
    private String token;
    private String userName, planningDate, locationName, locationId, trialTypeId, trialTypeName, observationType;
    private TextView farmersNameTextView, locationTextView, plantingDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_field_layout_activity);

        farmersNameTextView = (TextView) findViewById(R.id.farmersNameTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        plantingDateTextView = (TextView) findViewById(R.id.plantingDateTextView);

        mCredentialsStorage = getSharedPreferences("AppSharedPreferences", MODE_PRIVATE);
        token = mCredentialsStorage.getString(LoginActivity.TOKEN, null);
        userName = mCredentialsStorage.getString(LoginActivity.USERNAME, null);

        observationType = getIntent().getStringExtra("observationType");
        planningDate = getIntent().getStringExtra("planningDate");
        locationName = getIntent().getStringExtra("locationName");
        locationId = getIntent().getStringExtra("locationId");
        trialTypeId = getIntent().getStringExtra("trialTypeId");
        trialTypeName = getIntent().getStringExtra("trialTypeName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(observationType.contains("Planting")){
            toolbar.setTitle(getString(R.string.at_planting));
            observation = getString(R.string.at_planting);
        } else if(observationType.contains("20 DAP")){
            toolbar.setTitle(getString(R.string.dap_20));
            observation = getString(R.string.dap_20);
        } else if(observationType.contains("30 DAP")){
            toolbar.setTitle(getString(R.string.dap_30));
            observation = getString(R.string.dap_30);
        } else if(observationType.contains("45 DAP")){
            toolbar.setTitle(getString(R.string.dap_45));
            observation = getString(R.string.dap_45);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashScreen = new Intent(FieldLayoutActivity.this, MainMenuActivity.class);
                startActivity(splashScreen);
            }
        });
        RecyclerView ParentRecyclerViewItem = findViewById(R.id.recyclerListView);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LayoutDetailsPojo> call = apiInterface.layoutDetails(userName, planningDate, locationName, locationId, trialTypeName, trialTypeId);
        call.enqueue(new Callback<LayoutDetailsPojo>() {
            @Override
            public void onResponse(Call<LayoutDetailsPojo> call, Response<LayoutDetailsPojo> response) {
                if (response.isSuccessful()) {
                    LayoutDetailsPojo mainMenuResponse = response.body();
                        if(mainMenuResponse != null && mainMenuResponse.getData() != null){
                            LinearLayoutManager layoutManager = new LinearLayoutManager(FieldLayoutActivity.this);

                            LocationDetailsDataPojo data = mainMenuResponse.getData().get(0);

                            farmersNameTextView.setText("Farmer's Name: " + data.getFarmer_name());
                            locationTextView.setText("Location: " + data.getLocation_name());
                            plantingDateTextView.setText("Planting Date: " + data.getStart_date());

                            // Pass the arguments
                            // to the parentItemAdapter.
                            // These arguments are passed
                            // using a method ParentItemList()

                            RVParentItemAdapter parentItemAdapter = new RVParentItemAdapter(FieldLayoutActivity.this,
                                    mainMenuResponse.getData().get(0).getPurposes(), observation, data.getN_replications(), data.getN_observation_lines());

                            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
                            ParentRecyclerViewItem.setLayoutManager(layoutManager);
                        }
                    // Handle the successful response here
                } else {
                    // Handle the unsuccessful response here
                    UIUtils.customToastMsg(FieldLayoutActivity.this, "Error");
                }
            }

            @Override
            public void onFailure(Call<LayoutDetailsPojo> call, Throwable t) {
                // Handle the failure here
            }
        });
    }

    private ArrayList<RVParentItem> ParentItemList() {
        ArrayList<RVParentItem> itemList = new ArrayList<>();

        RVParentItem item = new RVParentItem("Table", ChildItemList1());
        itemList.add(item);
        RVParentItem item1 = new RVParentItem("Crisp", ChildItemList());
        itemList.add(item1);
        RVParentItem item2 = new RVParentItem("FF", ChildItemList());
        itemList.add(item2);
        RVParentItem item3 = new RVParentItem("Table/Baker", ChildItemList());
        itemList.add(item3);

        return itemList;
    }

    private ArrayList<RVChildItem> ChildItemList1() {
        ArrayList<RVChildItem> ChildItemList = new ArrayList<>();

        ChildItemList.add(new RVChildItem("5001"));
        ChildItemList.add(new RVChildItem("5002"));
        ChildItemList.add(new RVChildItem("5003"));
        ChildItemList.add(new RVChildItem("5003"));
        ChildItemList.add(new RVChildItem("5001"));
        ChildItemList.add(new RVChildItem("5002"));
        ChildItemList.add(new RVChildItem("5002"));
        ChildItemList.add(new RVChildItem("5003"));
        ChildItemList.add(new RVChildItem("5001"));
        return ChildItemList;
    }

    private ArrayList<RVChildItem> ChildItemList() {
        ArrayList<RVChildItem> ChildItemList = new ArrayList<>();

        ChildItemList.add(new RVChildItem("5001"));
        ChildItemList.add(new RVChildItem("5002"));
        ChildItemList.add(new RVChildItem("5003"));
        ChildItemList.add(new RVChildItem("5004"));

        return ChildItemList;
    }
}
