package com.example.nbu.service.player;

import com.example.nbu.R;
import com.example.nbu.service.pojos.Armor;
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

    public final int inventoryImage = R.drawable.inventory;

    //region initialization
    private Inventory() {
        items = new ArrayList<>();
    }

    private static final Inventory instance = new Inventory();
    //endregion

    public void addItem(Item item) {
        if (items.size() > 19) {
            throw new IllegalStateException("Inventory is fill");
        }
        items.add(item);
    }

    public void clearInventory() {
        items.clear();
        headPiece = null;
        armsPiece = null;
        chestPiece = null;
        legsPiece = null;
        feetPiece = null;
        weapon = null;
        gold = 0;
    }

    public static void loadInventory(Inventory inventory) {
        instance.clearInventory();
        instance.items.addAll(inventory.items);
        instance.headPiece = inventory.headPiece;
        instance.armsPiece = inventory.armsPiece;
        instance.chestPiece = inventory.chestPiece;
        instance.legsPiece = inventory.legsPiece;
        instance.feetPiece = inventory.feetPiece;
        instance.weapon = inventory.weapon;
        instance.gold = inventory.gold;

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
        Adventurer adventurer = Adventurer.getInstance();
        if (armor.getAgiRequirement() > adventurer.getAgility()) {
            return false;
        }
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
        adventurer.applyItemBonuses(armor);
        return true;
    }

    private boolean tryToEquipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            tryToUnEquipItem(this.weapon);
        }
        Adventurer adventurer = Adventurer.getInstance();
        if (weapon.getStrRequirement() > adventurer.getStrength()) {
            return false;
        }
        this.weapon = weapon;
        adventurer.applyItemBonuses(weapon);
        return true;
    }

    public boolean tryToDropItem(Item item) {
        return items.remove(item);
    }

    public boolean tryToUnEquipItem(Item item) {
        if (items.size() >= 20) {
            return false;
        }
        Adventurer adventurer = Adventurer.getInstance();
        boolean unEquipPerformed = false;
        if (headPiece != null && headPiece.equals(item)) {
            headPiece = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (chestPiece != null && chestPiece.equals(item)) {
            chestPiece = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (armsPiece != null && armsPiece.equals(item)) {
            armsPiece = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (feetPiece != null && feetPiece.equals(item)) {
            feetPiece = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (legsPiece != null && legsPiece.equals(item)) {
            legsPiece = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (weapon != null && weapon.equals(item)) {
            weapon = null;
            items.add(item);
            unEquipPerformed = true;
        }
        if (unEquipPerformed) {
            adventurer.removeItemBonuses(item);
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
