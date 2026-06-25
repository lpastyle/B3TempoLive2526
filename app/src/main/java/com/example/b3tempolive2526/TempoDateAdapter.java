package com.example.b3tempolive2526;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b3tempolive2526.databinding.TempoDateItemBinding;
import com.example.b3tempolive2526.model.TempoDate;

import java.util.List;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {
    private final List<TempoDate> tempoDates;
    private final Context context;

    // Ctor
    public TempoDateAdapter(Context context, List<TempoDate> tempoDates) {
        this.tempoDates = tempoDates;
        this.context = context;
    }

    @NonNull
    @Override
    public TempoDateAdapter.TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TempoDateViewHolder(TempoDateItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false));
        /*View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item, parent,false);
        return new TempoDateViewHolder(v); */
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateAdapter.TempoDateViewHolder holder, int position) {
        holder.binding.dateTv.setText(tempoDates.get(position).dateApplication);
        holder.binding.colorFl.setBackgroundColor(ContextCompat.getColor(context, tempoDates.get(position).statut.getColorResId()));

        /*holder.dateTv.setText(tempoDates.get(position).dateApplication);
        holder.colorFl.setBackgroundColor(ContextCompat.getColor(context, tempoDates.get(position).statut.getColorResId()));
        */
    }

    @Override
    public int getItemCount() {
        return tempoDates.size();
    }

   /* public static class TempoDateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv;
        FrameLayout colorFl;

        public TempoDateViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.date_tv);
            colorFl = itemView.findViewById(R.id.color_fl);
        }
    } */

    public static class TempoDateViewHolder extends RecyclerView.ViewHolder {
        TempoDateItemBinding binding;

        public TempoDateViewHolder(@NonNull TempoDateItemBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
}
