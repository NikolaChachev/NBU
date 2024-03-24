package com.example.nbu.service.pojos;

import androidx.annotation.NonNull;

public class Armor extends Item{
    private final int agiRequirement;
    private final int armor;
    private final int rating;
    private final int slot; // 0 - head 1 - chest piece 2 -  arms 3 - legs 4 - feet

    public Armor(String name, int value, int agiRequirement, int armor, int rating, int slot) {
        super(name, value);
        if (agiRequirement < 0 || armor < 0 || rating < 0 || slot < 0 || slot > 5) {
            this.agiRequirement = 0;
            this.armor = 0;
            this.rating = 0;
            this.slot = 3;
        } else {
            this.agiRequirement = agiRequirement;
            this.armor = armor;
            this.rating = rating;
            this.slot = slot;
        }
    }

    int getAgiRequirement() {
        return agiRequirement;
    }

    public int getArmor() {
        return armor;
    }

    public int getSlot() {
        return slot;
    }

    @NonNull
    @Override
    public String toString() {
        String slot;
        if (this.slot == 0) {
            slot = "head";
        } else if (this.slot == 1) {
            slot = "chest";
        } else if (this.slot == 2) {
            slot = "arms";
        } else if (this.slot == 3) {
            slot = "legs";
        } else {
            slot = "feet";
        }
        return "name: " + super.getName() + " value: " + super.getValue() + " slot:" + slot
                + " agiRequirement: " + agiRequirement + " armor: " + armor + " rating: " + rating;
    }

}
