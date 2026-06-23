package com.example.b3tempolive2526;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.b3tempolive2526.model.TempoDate;
import com.example.b3tempolive2526.model.TempoDaysLeft;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    IEdfApi edfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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

        // Create an asynchronous call
        Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft(
                IEdfApi.API_OPTION_PARAM_VALUE,
                "2026-06-23"
        );

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(Call<TempoDaysLeft> call, Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpsURLConnection.HTTP_OK && tempoDaysLeft != null) {
                   for (int i=0; i < tempoDaysLeft.content.size(); i++) {
                       Log.d(LOG_TAG, "typeJourEff=" + tempoDaysLeft.content.get(i).typeJourEff);
                       Log.d(LOG_TAG, "nombreJours=" + tempoDaysLeft.content.get(i).nombreJours);
                       Log.d(LOG_TAG, "nombreJoursTires=" + tempoDaysLeft.content.get(i).nombreJoursTires);
                   }
                } else {
                    Log.w(LOG_TAG,"Call to getTempoDaysLeft() failed with error code = "+response.code());
                }
            }

            @Override
            public void onFailure(Call<TempoDaysLeft> call, Throwable t) {
                Log.w(LOG_TAG,"Call to getTempoDaysLeft() failed");
            }
        });
    }
}