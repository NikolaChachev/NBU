package com.example.nbu.presentation.character;

import androidx.annotation.NonNull;

public abstract class BaseCharacter {
    private final String name;

    protected int level;
    protected double maxHealth;
    protected double currentHealth;
    protected int armor;
    protected int speed;
    protected int strength;
    protected int agility;
    protected double baseDamage;

    public BaseCharacter(String name, int level, double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        if (name == null || name.isEmpty() || level < 1 || maxHealth < 1 || armor < 0 || strength < 0 || agility < 0
                || baseDamage < 0 || speed < 0) {
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

    public String getName() {
        return name;
    }

    public int getArmor() {
        return armor;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void heal(){
        this.currentHealth = this.maxHealth;
    }

    public abstract double getDamage();

    public void takeDamage(double damage) {
        this.currentHealth = this.currentHealth - damage < 0 ? 0 : this.currentHealth - damage;
    }

    public boolean isDead() {
        return this.currentHealth < 1;
    }

    public abstract int getDodgeActionColor();
    public abstract int getBlockActionColor();
    public abstract int getCritActionColor();
    public abstract int getDamageActionColor();
    @NonNull
    @Override
    public String toString() {
        return this.name + " | lvl:" + this.level + "  hp:" + this.maxHealth
                + "\nstrength:" + this.strength + " agility:" + this.agility
                + "\narmor:" + this.armor + " damage:" + this.baseDamage + " speed:" + speed;
    }
}
