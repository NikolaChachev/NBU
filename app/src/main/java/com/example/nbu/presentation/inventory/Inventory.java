package com.example.nbu.presentation.inventory;

import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<Item> items;

    //region initialization
    private Inventory() {
        items = new ArrayList<>();
        //todo thi is for testing, remove later on.
        Weapon weapon = new Weapon("Axe", 200, 5, 10, 2);
        Weapon weapon1 = new Weapon("Sword", 100, 2, 4, 1);
        Armor armor = new Armor("golden", 1000, 30, 40, 1, 1);
        items.add(weapon1);
        items.add(weapon);
    }

    private static final Inventory instance = new Inventory();
    //endregion

    public void putItem(Item item) {
        if (items.size() > 19) throw new IllegalStateException("Inventory is fill");
        items.add(item);
    }

    public void addMany(List<Item> list) {
        if(list.size() + items.size() < 20){
            items.addAll(list);
            return;
        }
        for (Item item : list) {
            if(items.size() > 19) return;
            items.add(item);
        }
    }

    public Item removeItem(int index) {
        if (index < 0 || index > items.size() - 1) throw new IndexOutOfBoundsException();
        return items.remove(index);
    }

    public List<Item> removeAll() {
        List<Item> copy = new ArrayList<>(items);
        items.clear();
        return copy;
    }

    public List<Item> getItems(){
        return items;
    }


    public static Inventory getInstance() {
        return instance;
    }

}
