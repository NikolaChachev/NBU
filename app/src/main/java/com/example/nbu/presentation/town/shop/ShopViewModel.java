package com.example.nbu.presentation.town.shop;

import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.presentation.combat.CombatUtil;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import com.example.nbu.service.pojos.Item;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class ShopViewModel extends AbstractViewModel {

    private int shopTypePersistent = 0;

    public void setShopTypePersistent(int type) {
        this.shopTypePersistent = type;
    }

    public int getShopTypePersistent() {
        return shopTypePersistent;
    }

    public List<Item> generateItemsBasedOnShopType() {
        return shopTypePersistent == 0 ? getArmorSmithItems() : getWeaponSmithItems();
    }

    private List<Item> getWeaponSmithItems() {
        return new ArrayList<>(CombatUtil.weapons);
    }

    private List<Item> getArmorSmithItems() {
        return new ArrayList<>(CombatUtil.armorSets);
    }

    @Inject
    public ShopViewModel(final ACoroutineContextProvider provider) {
        super(provider);
    }
}
