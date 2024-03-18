package com.example.nbu.service.pojos;

public class Weapon extends Item {
    private int StrRequirement;
    private double bonusDamage;
    private int rating;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-10s", super.getName()))
                .append(String.format("%-14s", " | value: " + super.getValue()))
                .append(String.format("%-28s"," | Strength requirement: " + StrRequirement))
                .append(String.format("%-22s", " | Bonus damage: " + bonusDamage))
                .append(" | rating: ")
                .append(rating);
//        String data = sb.toString() + "| value: " + super.getValue() +
//                " |Strength requirement: " + StrRequirement +
//                " |Bonus damage: " + bonusDamage +
//                " |rating: " + rating;
        return sb.toString();
    }
}
