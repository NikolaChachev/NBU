package com.example.nbu.presentation.inventory;

import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.ArmorTypes;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<Item> items;

    Item headPiece;

    Item chestPiece;

    Item armsPiece;

    Item feetPiece;

    Item legsPiece;

    int gold;

    //region initialization
    private Inventory() {
        items = new ArrayList<>();
        //todo thi is for testing, remove later on.
        Weapon weapon = new Weapon("Axe", 200, 5, 10, 2);
        Weapon weapon1 = new Weapon("Sword", 100, 2, 4, 1);
        Armor armor = new Armor("golden feet", 1000, 30, 40, 1, ArmorTypes.FEET);
        items.add(weapon1);
        items.add(weapon);
        items.add(weapon);
        items.add(weapon);
        items.add(weapon);
        items.add(weapon);
        items.add(weapon);
        items.add(weapon1);
        items.add(weapon1);
        items.add(weapon1);
        items.add(armor);
        headPiece = new Armor("horned helm", 500, 3, 10, 1, ArmorTypes.HEAD);
    }

    private static final Inventory instance = new Inventory();
    //endregion

    public void putItem(Item item) {
        if (items.size() > 19) {
            throw new IllegalStateException("Inventory is fill");
        }
        items.add(item);
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addMany(List<Item> list) {
        if (list.size() + items.size() < 20) {
            items.addAll(list);
            return;
        }
        for (Item item : list) {
            if (items.size() > 19) {
                return;
            }
            items.add(item);
        }
    }

    public Item removeItem(int index) {
        if (index < 0 || index > items.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return items.remove(index);
    }

    public List<Item> removeAll() {
        List<Item> copy = new ArrayList<>(items);
        items.clear();
        return copy;
    }

    public Item getHeadPiece() {
        return headPiece;
    }

    public Item getChestPiece() {
        return chestPiece;
    }

    public Item getArmsPiece() {
        return armsPiece;
    }

    public Item getFeetPiece() {
        return feetPiece;
    }

    public Item getLegsPiece() {
        return legsPiece;
    }

    public List<Item> getItems() {
        return items;
    }


    public static Inventory getInstance() {
        return instance;
    }

}
