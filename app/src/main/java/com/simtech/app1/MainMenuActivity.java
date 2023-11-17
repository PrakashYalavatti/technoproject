package com.simtech.app1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.simtech.app1.adapter.MainMenuAdapter;
import com.simtech.app1.apiservices.APIClient;
import com.simtech.app1.apiservices.APIInterface;
import com.simtech.app1.apiservices.apirequestresponse.MainMenuResponse;
import com.simtech.app1.apputils.UIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity {
    APIInterface apiInterface;
    RecyclerView rvDashboard;
    private SharedPreferences mCredentialsStorage;
    private String token;
    private String userName;
    private Button btnDashboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mCredentialsStorage = getSharedPreferences("AppSharedPreferences", MODE_PRIVATE);
        token = mCredentialsStorage.getString(LoginActivity.TOKEN, null);
        userName = mCredentialsStorage.getString(LoginActivity.USERNAME, null);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.main_menu));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splashScreen = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(splashScreen);
            }
        });

        rvDashboard = findViewById(R.id.rvDashboard);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MainMenuResponse> call = apiInterface.mainMenu(userName);
        call.enqueue(new Callback<MainMenuResponse>() {
            @Override
            public void onResponse(Call<MainMenuResponse> call, Response<MainMenuResponse> response) {
                if (response.isSuccessful()) {
                    MainMenuResponse mainMenuResponse = response.body();
                    if (mainMenuResponse != null && !mainMenuResponse.getData().isEmpty()) {
                        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(mainMenuResponse.getData(), MainMenuActivity.this);
                        rvDashboard.setAdapter(mainMenuAdapter);
                    }
                    // Handle the successful response here
                } else {
                    // Handle the unsuccessful response here
                    UIUtils.customToastMsg(MainMenuActivity.this, "Error");
                }
            }

            @Override
            public void onFailure(Call<MainMenuResponse> call, Throwable t) {
                // Handle the failure here
            }
        });
    }

    /*private ArrayList<LocationObservationPojo> createSampleLocationData() {

        ArrayList<LocationObservationPojo> sampleExperiments = new ArrayList<>();

// Experiment 1
        ArrayList<TrialObservationPojo> trials1 = new ArrayList<>();
        trials1.add(new TrialObservationPojo(1,"Regular, PET", 1, "At planting"));
        trials1.add(new TrialObservationPojo(2, "FastTrack PET", 1, "At planting"));
        trials1.add(new TrialObservationPojo(3, "CT", 1, "At planting"));
        sampleExperiments.add(new LocationObservationPojo("2023-10-25", "Sambhal", trials1));

// Experiment 2
        ArrayList<TrialObservationPojo> trials2 = new ArrayList<>();
        trials2.add(new TrialObservationPojo(2, "FastTrack PET", 3,"Emergence 30 DAP"));
        trials2.add(new TrialObservationPojo(1, "Regular, PET", 3, "Emergence 30 DAP"));
        sampleExperiments.add(new LocationObservationPojo("2023-11-25", "Chikkaballapur", trials2));

// Experiment 3
        ArrayList<TrialObservationPojo> trials3 = new ArrayList<>();
        trials3.add(new TrialObservationPojo(3, "CT", 4, "45 DAP"));
        sampleExperiments.add(new LocationObservationPojo("2023-12-10", "Alipurduar", trials3));

        return sampleExperiments;
    }*/
    /*@Override
    public void onButtonLayoutPendingClicked() {
        UIUtils.displayAlertDialog(this, "Please wait... \nLayout is pending...", "Alert", false, (dialog, which) -> dialog.dismiss(), null, null);
    }

    @Override
    public void onButtonPlantingClicked() {
        Intent intent = new Intent(MainMenuActivity.this, PlantationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onButton20DQPClicked() {
        Intent intent = new Intent(MainMenuActivity.this, FieldLayoutActivity.class);
        intent.putExtra("SCREEN_FROM", "20DAP");
        startActivity(intent);
    }

    @Override
    public void onButton30DQPClicked() {
        Intent intent = new Intent(MainMenuActivity.this, FieldLayoutActivity.class);
            intent.putExtra("SCREEN_FROM", "30DAP");
        startActivity(intent);
    }*/
}

