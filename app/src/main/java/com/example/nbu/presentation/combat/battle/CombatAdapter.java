package com.example.nbu.presentation.combat.battle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbu.databinding.LayoutCombatLogBinding;

import java.util.LinkedList;
import java.util.List;

public class CombatAdapter extends RecyclerView.Adapter<CombatAdapter.CombatViewHolder> {

    private final LinkedList<CombatLog> dataSet;

    private final Context context;

    CombatAdapter(List<CombatLog> dataSet, Context context) {
        this.dataSet = new LinkedList<>(dataSet);
        this.context = context;
    }

    @NonNull
    @Override
    public CombatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        LayoutCombatLogBinding binding = LayoutCombatLogBinding.inflate(inf, parent, false);
        return new CombatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CombatViewHolder holder, int position) {
        CombatLog log = dataSet.get(position);
        holder.bind(log, context);
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public void insertItem(CombatLog item) {
        dataSet.add(item);
        notifyItemInserted(dataSet.size() - 1);
    }

    public void insertItems(List<CombatLog> newLogs) {
        int position = dataSet.size();
        dataSet.addAll(newLogs);
        notifyItemRangeInserted(position, dataSet.size() - 1);
    }

    public static class CombatViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCombatLogBinding binding;

        public CombatViewHolder(@NonNull LayoutCombatLogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CombatLog log, Context context) {
            binding.combatLogText.setText(log.log);
            binding.combatLogText.setTextColor(ContextCompat.getColor(context, log.colorResource));
        }

    }
}
