package com.example.nbu.service.pojos;

import androidx.annotation.NonNull;

public class Weapon extends Item {
    private final int StrRequirement;
    private final double bonusDamage;
    private final int rating;

    public Weapon(String name, int value, int strRequirement, double bonusDamage, int rating) {
        super(name, value);
        StrRequirement = strRequirement;
        this.bonusDamage = bonusDamage;
        this.rating = rating;
    }

    public int getStrRequirement() {
        return StrRequirement;
    }

    public double getBonusDamage() {
        return bonusDamage;
    }

    public int getRating() {
        return rating;
    }

    @NonNull
    @Override
    public String toString() {

        //        String data = sb.toString() + "| value: " + super.getValue() +
//                " |Strength requirement: " + StrRequirement +
//                " |Bonus damage: " + bonusDamage +
//                " |rating: " + rating;
        //todo update to somethign ebetter
        return String.format("%-10s", super.getName()) +
                String.format("%-14s", " | value: " + super.getValue() + "g") +
                String.format("%-28s", " | Strength requirement: " + StrRequirement) +
                String.format("%-22s", " | Bonus damage: " + bonusDamage) +
                " | rating: " +
                rating;
    }
}
