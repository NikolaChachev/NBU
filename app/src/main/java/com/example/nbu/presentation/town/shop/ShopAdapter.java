package com.example.nbu.presentation.town.shop;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nbu.databinding.LayoutShopItemBinding;
import com.example.nbu.presentation.inventory.IOnItemClick;
import com.example.nbu.presentation.town.shop.ShopAdapter.ShopViewHolder;
import com.example.nbu.service.pojos.Item;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    private final List<Item> dataSet;

    protected final IOnItemClick onItemClick;

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        private final LayoutShopItemBinding binding;

        public ShopViewHolder(@NonNull final LayoutShopItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Item item, IOnItemClick clickListener) {
            binding.shopItemTitle.setText(item.toString());
            binding.getRoot().setOnClickListener(v -> clickListener.onItemClick(item, v));
        }
    }

    public ShopAdapter(final List<Item> dataSet, IOnItemClick clickListener) {
        this.dataSet = dataSet;
        this.onItemClick = clickListener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        LayoutShopItemBinding binding = LayoutShopItemBinding.inflate(inf, parent, false);
        return new ShopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopViewHolder holder, final int position) {
        Item item = dataSet.get(position);
        holder.bind(item, onItemClick);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
