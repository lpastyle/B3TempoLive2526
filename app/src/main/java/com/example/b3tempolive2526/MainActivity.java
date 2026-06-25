package com.example.b3tempolive2526;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.b3tempolive2526.databinding.ActivityMainBinding;
import com.example.b3tempolive2526.model.TempoDate;
import com.example.b3tempolive2526.model.TempoDaysLeft;
import com.example.b3tempolive2526.model.TempoHistory;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    ActivityMainBinding binding;
    IEdfApi edfApi;

    // view model
    ArrayList<TempoDate> tempoCalendar = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofitClient = ApiClient.get();
        if (retrofitClient != null) {
            edfApi = retrofitClient.create(IEdfApi.class);
        } else {
            Log.e(LOG_TAG, "Retrofit client init failed");
            finish();
        }
        updateNbTempoDaysLeft();
        updateTempoHistory();

    }

    /*
     *  ----------------------- Helper Methods -------------------------
     */

    void updateNbTempoDaysLeft() {
        // Create call to getTempoDaysLeft
        Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft(
                IEdfApi.API_OPTION_PARAM_VALUE,
                Tools.getNowDate()
        );

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysLeft> call, @NonNull Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysLeft != null) {
                    for (int i = 0; i < tempoDaysLeft.content.size(); i++) {
                        Log.d(LOG_TAG, "typeJourEff[" + i + "] = " + tempoDaysLeft.content.get(i).typeJourEff);
                        Log.d(LOG_TAG, "nombreJours[" + i + "] = " + tempoDaysLeft.content.get(i).nombreJours);
                        Log.d(LOG_TAG, "nombreJoursTirés[" + i + "] = " + tempoDaysLeft.content.get(i).nombreJoursTires);
                    }
                    setTempoDaysLeft(tempoDaysLeft.content);
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysLeft() failed with error code " + response.code());
                }
            }
            @Override
            public void onFailure(@NonNull Call<TempoDaysLeft> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysLeft() failed ");
            }
        });
    }

    void setTempoDaysLeft(List<TempoDaysLeft.Content> contents) {
        for (TempoDaysLeft.Content item : contents) {
            switch (item.typeJourEff) {
                // using hardcoded values is not reliable. This will be improved later
                case RED: binding.redDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
                case WHITE: binding.whiteDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
                case BLUE: binding.blueDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
            }
        }
    }

    void updateTempoHistory() {
        // Create call to getTempoHistory
        Call<TempoHistory> call = edfApi.getTempoHistory(
                IEdfApi.API_OPTION_PARAM_VALUE,
                Tools.getNowDate(),
                Tools.getTomorrowDate(),
                IEdfApi.API_CONSUMER_ID_PARAM_VALUE);

        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                tempoCalendar.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    Log.d(LOG_TAG,"Got tempo history for " + response.body().content.options.size() + " option(s)");
                    setTempoCalendar(response.body());
                } else {
                    Log.e(LOG_TAG,"Call to getTempoHistory() returned error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                Log.e(LOG_TAG,"Call to getTempoHistory() failed");
            }
        });
    }

    void setTempoCalendar(TempoHistory tempoHistory) {
        for(TempoHistory.Option item : tempoHistory.content.options) {
            if (item.option.equals(IEdfApi.API_OPTION_PARAM_VALUE)) {
                tempoCalendar.addAll(item.calendrier);
                break;
            }
        }
        if (tempoCalendar.isEmpty()) {
            Log.w(LOG_TAG,"No data found for option "+ IEdfApi.API_OPTION_PARAM_VALUE);
        } else {
            for(TempoDate date : tempoCalendar) {
                Log.d(LOG_TAG, date.dateApplication + " = " + date.statut);
            }
            // update custom views
            int historySize = tempoCalendar.size();
            if (historySize > 1) {
                binding.todayDcv.setDayCircleColor(tempoCalendar.get(historySize - 2).statut);
                binding.tomorrowDcv.setDayCircleColor(tempoCalendar.get(historySize - 1).statut);
            }
        }
    }


}