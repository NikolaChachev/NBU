package com.example.nbu.service.pojos;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Armor extends Item {
    private final int agiRequirement;
    private final int armor;
    private final int rating;
    private final ArmorTypes slot;

    public Armor(String name, int value, int agiRequirement, int armor, int rating, ArmorTypes slot) {
        super(name, value);
        if (agiRequirement < 0 || armor < 0 || rating < 0) {
            this.agiRequirement = 0;
            this.armor = 0;
            this.rating = 0;
            this.slot = ArmorTypes.HEAD;
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

    public ArmorTypes getSlot() {
        return slot;
    }

    @NonNull
    @Override
    public String toString() {
//        String slot;
//        slot = this.slot.toString().toLowerCase(Locale.ROOT);
//        return "name: " + super.getName() + " value: " + super.getValue() + " slot:" + slot
//                + " agiRequirement: " + agiRequirement + " armor: " + armor + " rating: " + rating;

        return String.format("%-10s", super.getName()) +
                String.format("%-14s", " | value: " + super.getValue() + "g") +
                String.format("%-28s", " | Agility requirement: " + agiRequirement) +
                String.format("%-22s", " | Bonus armor: " + armor) +
                String.format("%-14s", " | slot: " + slot) +
                " | rating: " +
                rating;
    }

}
