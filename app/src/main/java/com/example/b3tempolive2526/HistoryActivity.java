package com.example.b3tempolive2526;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.b3tempolive2526.databinding.ActivityHistoryBinding;
import com.example.b3tempolive2526.model.TempoDate;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();
    ActivityHistoryBinding binding;

    // Data model
    private final ArrayList<TempoDate> tempoCalendar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<TempoDate> parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(MainActivity.TEMPO_CALENDAR_EXTRA_KEY);
        if (parcelableArrayListExtra != null) {
            tempoCalendar.addAll(parcelableArrayListExtra);
        } else {
            Log.d(LOG_TAG,"Empty tempo calendar was passed to " + LOG_TAG);
            finish();
        }
    }
}