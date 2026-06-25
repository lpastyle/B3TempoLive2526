package com.example.b3tempolive2526;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {
    @NonNull
    @Override
    public TempoDateAdapter.TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateAdapter.TempoDateViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TempoDateViewHolder extends RecyclerView.ViewHolder {

        public TempoDateViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
