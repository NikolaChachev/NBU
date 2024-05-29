package com.example.nbu.presentation.inventory;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nbu.databinding.LayoutInventoryItemBinding;
import com.example.nbu.presentation.inventory.InventoryAdapter.InventoryViewHolder;
import com.example.nbu.service.pojos.Item;
import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> {

    private final List<Item> dataSet;

    protected final IOnItemClick onItemClick;

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {

        private final LayoutInventoryItemBinding binding;

        public InventoryViewHolder(LayoutInventoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Item item, IOnItemClick clickListener) {
            binding.itemTitle.setText(item.toString());
            binding.getRoot().setOnClickListener(v -> clickListener.onItemClick(item, v));
        }
    }

    public InventoryAdapter(List<Item> dataSet, IOnItemClick clickListener) {
        this.dataSet = new ArrayList<>(dataSet);
        this.onItemClick = clickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshItemsData(List<Item> newData){
        this.dataSet.clear();
        this.dataSet.addAll(newData);
        //this is not a good idea generally, but it is done to save time.
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        LayoutInventoryItemBinding binding = LayoutInventoryItemBinding.inflate(inf, parent, false);
        return new InventoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder viewHolder, final int position) {
        viewHolder.bind(dataSet.get(position), onItemClick);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}