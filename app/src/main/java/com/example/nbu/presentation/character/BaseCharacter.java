package com.example.nbu.presentation.character;

public abstract class BaseCharacter {
    private String name;

    private int level;
    private double maxHealth;
    private double currentHealth;
    private int armor;
    private int speed;
    private int strength;
    private int agility;
    private double baseDamage;

    public BaseCharacter(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        if (name == null || name.isEmpty() || level < 1 || maxHealth < 1 || armor < 0 || strength < 0 || agility < 0
                || baseDamage < 0 || speed < 0){
            throw new IllegalArgumentException("bad data parsed to constructor");
        }
        this.name = name;
        this.level = level;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.armor = armor;
        this.speed = speed;
        this.strength = strength;
        this.agility = agility;
        this.baseDamage = baseDamage;
    }

    public abstract void hitTarget(BaseCharacter target);

    public int getLevel() {
        return level;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return this.name + " | lvl:" + this.level + "  hp:" + this.maxHealth
                + "\nstrength:" + this.strength + " agility:" + this.agility
                + "\narmor:" + this.armor + " damage:" + this.baseDamage + " speed:" + speed;
    }
}
