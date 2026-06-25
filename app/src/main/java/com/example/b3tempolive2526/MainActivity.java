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

import java.net.HttpURLConnection;
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
                case "TEMPO_ROUGE" : binding.redDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
                case "TEMPO_BLANC" : binding.whiteDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
                case "TEMPO_BLEU" : binding.blueDaysTv.setText(Tools.getDaysLeftFromContent(item));
                    break;
            }
        }
    }

}