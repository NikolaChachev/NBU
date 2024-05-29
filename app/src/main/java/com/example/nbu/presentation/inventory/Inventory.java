package com.example.nbu.presentation.inventory;

import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.ArmorTypes;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<Item> items;

    private Item headPiece;

    private Item chestPiece;

    private Item armsPiece;

    private Item feetPiece;

    private Item legsPiece;

    private Item weapon;

    private int gold;

    //region initialization
    private Inventory() {
        items = new ArrayList<>();
        //todo thi is for testing, remove later on.
        Weapon weapon = new Weapon("Axe", 200, 5, 10, 2);
        Weapon weapon1 = new Weapon("Sword", 100, 2, 4, 1);
        Armor armor = new Armor("golden feet", 1000, 30, 40, 1, ArmorTypes.FEET);
        items.add(weapon1);
        items.add(armor);
        headPiece = new Armor("horned helm", 500, 3, 10, 1, ArmorTypes.HEAD);
    }

    private static final Inventory instance = new Inventory();
    //endregion

    public void addItem(Item item) {
        if (items.size() > 19) {
            throw new IllegalStateException("Inventory is fill");
        }
        items.add(item);
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getGold() {
        return this.gold;
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

    public Item getWeapon() {
        return weapon;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean tryToEquipItem(Item item) {
        if (!(item instanceof Armor) && !(item instanceof Weapon)) {
            return false;
        }

        boolean removed = items.remove(item);
        if (!removed) {
            return false;
        }
        if (item instanceof Armor) {
            return tryToEquipArmor((Armor) item);
        } else {
            return tryToEquipWeapon((Weapon) item);
        }
    }

    private boolean tryToEquipArmor(Armor armor) {
        switch (armor.getSlot()) {
            case HEAD:
                if (tryToUnEquipItem(headPiece)) {
                    headPiece = armor;
                }
                break;
            case CHEST:
                if (tryToUnEquipItem(chestPiece)) {
                    chestPiece = armor;
                }
                break;
            case ARMS:
                if (tryToUnEquipItem(armsPiece)) {
                    armsPiece = armor;
                }
                break;
            case LEGS:
                if (tryToUnEquipItem(legsPiece)) {
                    legsPiece = armor;
                }
                break;
            case FEET:
                if (tryToUnEquipItem(feetPiece)) {
                    feetPiece = armor;
                }
                break;
        }
        return true;
    }

    private boolean tryToEquipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            tryToUnEquipItem(this.weapon);
        }
        this.weapon = weapon;
        return true;
    }

    public boolean tryToDropItem(Item item) {
        return items.remove(item);
    }

    public boolean tryToUnEquipItem(Item item) {
        if (items.size() >= 20) {
            return false;
        }
        if (headPiece != null && headPiece.equals(item)) {
            headPiece = null;
            items.add(item);
        }
        if (chestPiece != null && chestPiece.equals(item)) {
            chestPiece = null;
            items.add(item);
        }
        if (armsPiece != null && armsPiece.equals(item)) {
            armsPiece = null;
            items.add(item);
        }
        if (feetPiece != null && feetPiece.equals(item)) {
            feetPiece = null;
            items.add(item);
        }
        if (legsPiece != null && legsPiece.equals(item)) {
            legsPiece = null;
            items.add(item);
        }
        if (weapon != null && weapon.equals(item)) {
            weapon = null;
            items.add(item);
        }
        return true;
    }

    public boolean tryToSellItem(Item item) {
        boolean removed = items.remove(item);
        if (!removed) {
            return false;
        }
        gold += item.sellPrice();
        return true;
    }

    public static Inventory getInstance() {
        return instance;
    }

}
