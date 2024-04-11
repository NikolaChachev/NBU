package com.example.nbu.presentation.combat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbu.R;

import java.util.LinkedList;
import java.util.List;

public class CombatAdapter extends RecyclerView.Adapter<CombatAdapter.CombatViewHolder> {

    private LinkedList<CombatLog> dataSet;

    private Context context;

    CombatAdapter(List<CombatLog> dataSet, Context context) {
        this.dataSet = new LinkedList<>(dataSet);
        this.context = context;
    }

    @NonNull
    @Override
    public CombatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_combat_log, parent, false);
        return new CombatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CombatViewHolder holder, int position) {
        CombatLog log = dataSet.get(position);
        holder.getTextView().setText(log.log);
        holder.getTextView().setTextColor(ContextCompat.getColor(context, log.colorResource));
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
        int position = dataSet.size() - 1;
        dataSet.addAll(newLogs);
        notifyItemRangeInserted(position, dataSet.size() - 1);
    }

    public static class CombatViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public CombatViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.combat_log_text);
//            textView.setTextColor(Color.RED);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
