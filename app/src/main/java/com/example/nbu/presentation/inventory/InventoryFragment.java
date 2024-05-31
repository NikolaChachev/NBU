package com.example.nbu.presentation.inventory;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nbu.BR;
import com.example.nbu.R;
import com.example.nbu.databinding.FragmentInventoryBinding;
import com.example.nbu.mvvm.fragment.AbstractFragment;
import com.example.nbu.mvvm.vm.EmptyViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.service.pojos.Item;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InventoryFragment extends AbstractFragment<FragmentInventoryBinding,
        EmptyViewModel> {

    private InventoryAdapter adapter;

    private Inventory inventory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inventory = Inventory.getInstance();
        adapter = new InventoryAdapter(inventory.getItems(), this::handleItemClick);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.inventoryRecycleView.setAdapter(adapter);
        binding.inventoryRecycleView.setLayoutManager(layoutManager);
        setupEquipmentTexts();
        setupEquipmentClicks();
    }

    private void setupEquipmentClicks() {
        binding.inventoryEquipment.equipmentChestItem.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getChestPiece());
        });
        binding.inventoryEquipment.equipmentHeadItem.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getHeadPiece());
        });
        binding.inventoryEquipment.equipmentArms.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getArmsPiece());
        });
        binding.inventoryEquipment.equipmentLegs.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getLegsPiece());
        });
        binding.inventoryEquipment.equipmentFeet.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getFeetPiece());
        });
        binding.inventoryEquipment.equipmentWeapon.setOnClickListener(v -> {
            //todo dialog with stats and option to unEquip
            buildAlertDialog(inventory.getWeapon());
        });

    }

    private void buildAlertDialog(Item item) {
        final String title = "Equipment";
        final String message = item != null ? item.toString() : "No equipment";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.inventory_equipment_dialog_positive_button, (dialogInterface, i) -> {
                    //do nothing, just close the dialog
                });
        if (item != null) {
            builder.setNegativeButton(R.string.inventory_equipment_dialog_negative_button, (dialogInterface, i) -> {
                //try to unEquip item
                inventory.tryToUnEquipItem(item);
                refreshViews();
            });
        }
        builder.create().show();
    }

    private void handleItemClick(Item item, View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.inflate(R.menu.inventory_item_menu);
        Inventory inventory = Inventory.getInstance();
        popup.setOnMenuItemClickListener(menuItem -> {
            boolean result = false;
            if (menuItem.getItemId() == R.id.option_equip) {
                result = inventory.tryToEquipItem(item);
            } else if (menuItem.getItemId() == R.id.option_drop) {
                result = inventory.tryToDropItem(item);
            } else if (menuItem.getItemId() == R.id.option_sell) {
                result = inventory.tryToSellItem(item);
            }
            if (result) {
                refreshViews();
                createShortToast("Done!");
            } else {
                createShortToast("Oops, there was an error!");
            }
            return true;
        });
        popup.show();
    }

    private void refreshViews() {
        adapter.refreshItemsData(Inventory.getInstance().getItems());
        setupEquipmentTexts();
    }

    private void setupEquipmentTexts() {
        String text = inventory.getHeadPiece() != null ? inventory.getHeadPiece().getName() : "Empty";
        binding.inventoryEquipment.equipmentHeadItem.setText(getString(R.string.layout_equipment_helmet_text, text));
        text = inventory.getChestPiece() != null ? inventory.getChestPiece().getName() : "Empty";
        binding.inventoryEquipment.equipmentChestItem.setText(getString(R.string.layout_equipment_chest_text, text));
        text = inventory.getArmsPiece() != null ? inventory.getArmsPiece().getName() : "Empty";
        binding.inventoryEquipment.equipmentArms.setText(getString(R.string.layout_equipment_arms_text, text));
        text = inventory.getFeetPiece() != null ? inventory.getFeetPiece().getName() : "Empty";
        binding.inventoryEquipment.equipmentFeet.setText(getString(R.string.layout_equipment_feet_text, text));
        text = inventory.getLegsPiece() != null ? inventory.getLegsPiece().getName() : "Empty";
        binding.inventoryEquipment.equipmentLegs.setText(getString(R.string.layout_equipment_legs_text, text));
        text = inventory.getWeapon() != null ? inventory.getWeapon().getName() : "Empty";
        binding.inventoryEquipment.equipmentWeapon.setText(getString(R.string.layout_equipment_weapon_text, text));

        Adventurer adventurer = Adventurer.getInstance();
        text = String.valueOf(adventurer.getStrength());
        binding.inventoryStats.statsStrength.setText(getString(R.string.layout_stats_strength_text, text));
        text = String.valueOf(adventurer.getAgility());
        binding.inventoryStats.statsAgility.setText(getString(R.string.layout_stats_agility_text, text));
        text = String.valueOf(adventurer.getSpeed());
        binding.inventoryStats.statsSpeed.setText(getString(R.string.layout_stats_speed_text, text));
        text = String.valueOf((int) adventurer.getCurrentHealth());
        String maxHealth = String.valueOf((int) adventurer.getMaxHealth());
        binding.inventoryStats.statsHealth.setText(getString(R.string.layout_stats_health_text, text, maxHealth));
        text = String.valueOf(adventurer.getArmor());
        binding.inventoryStats.statsArmor.setText(getString(R.string.layout_stats_armor_text, text));
        text = String.valueOf(adventurer.getDamage());
        binding.inventoryStats.statsDamage.setText(getString(R.string.layout_stats_damage_text, text));
    }

    @Override
    protected int getViewModelResId() {
        return BR.EmptyVM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_inventory;
    }

    @Override
    protected Class<EmptyViewModel> getViewModelClass() {
        return EmptyViewModel.class;
    }
}
