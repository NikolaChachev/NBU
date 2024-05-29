package com.example.nbu.presentation.town.shop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nbu.R;
import com.example.nbu.databinding.ShopFragmentBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.presentation.inventory.Inventory;
import com.example.nbu.presentation.town.TownCenterFragment;
import com.example.nbu.service.pojos.Item;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShopFragment extends AbstractFragment<ShopFragmentBinding, ShopViewModel> {

    public static final String SHOP_TYPE_KEY = "shop_type";

    public static final int SHOP_ARMOR_SMITH = 0;

    public static final int SHOP_WEAPON_SMITH = 1;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        int shopType;
        if (args != null && !args.isEmpty() && args.containsKey(SHOP_TYPE_KEY)) {
            shopType = args.getInt(SHOP_TYPE_KEY);
            viewModel.setShopTypePersistent(shopType);
        } else {
            shopType = viewModel.getShopTypePersistent();
        }
        if (shopType == -1) {
            throw new IllegalStateException("Shop type cannot be undefined!");
        }
        setupRecyclerView();
        binding.shopNavigationButton.setOnClickListener(v -> navigateToView(TownCenterFragment.class, null));
    }

    private void setupRecyclerView() {
        ShopAdapter adapter = new ShopAdapter(viewModel.generateItemsBasedOnShopType(), this::setupOnClickForAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.shopRecyclerView.setAdapter(adapter);
        binding.shopRecyclerView.setLayoutManager(layoutManager);
    }

    private void setupOnClickForAdapter(Item item, View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.inflate(R.menu.shop_item_menu);
        popup.show();
        Menu menu = popup.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setOnMenuItemClickListener(mItem -> {
            Inventory inventory = Inventory.getInstance();
            if (inventory.getGold() >= item.buyPrice()) {
                inventory.addGold(-item.buyPrice());
                inventory.addItem(item);
            } else {
                createShortToast("Not enough gold to buy item!");
            }
            return true;
        });
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.shop_fragment;
    }

    @Override
    protected Class<ShopViewModel> getViewModelClass() {
        return ShopViewModel.class;
    }
}
