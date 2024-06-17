package com.example.nbu.service.player;

import com.example.nbu.R;
import com.example.nbu.presentation.combat.CombatUtil;
import com.example.nbu.service.character.BaseCharacter;
import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;

public class Adventurer extends BaseCharacter {

    private int currentExperience;

    private int requiredExperienceForNextLevel;

    private int expPointsToSpend = 0;

    private static Adventurer instance;

    private Adventurer(String name, int level, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, armor, speed, strength, agility, baseDamage);
        currentExperience = 0;
        requiredExperienceForNextLevel = 100;
    }

    public static Adventurer initializeAdventurer(String name) {
        instance = new Adventurer(name, 1, 0, 50, 2, 2, 5);
        return instance;
    }

    public static void restartAdventurerStats() {
        instance.currentExperience = 0;
        instance.requiredExperienceForNextLevel = 100;
        instance.expPointsToSpend = 0;
        instance.level = 1;
        instance.strength = 2;
        instance.maxHealth = instance.strength * 50;
        instance.currentHealth = instance.maxHealth;
        instance.armor = 0;
        instance.speed = 50;
        instance.agility = 2;
        instance.baseDamage = 5;
    }

    public static void updateAdventurerInstance(Adventurer adventurer) {
        instance.currentExperience = adventurer.currentExperience;
        instance.requiredExperienceForNextLevel = adventurer.requiredExperienceForNextLevel;
        instance.expPointsToSpend = adventurer.expPointsToSpend;
        instance.level = adventurer.level;
        instance.strength = adventurer.strength;
        instance.maxHealth = adventurer.maxHealth;
        instance.currentHealth = adventurer.currentHealth;
        instance.armor = adventurer.armor;
        instance.speed = adventurer.speed;
        instance.agility = adventurer.agility;
        instance.baseDamage = adventurer.baseDamage;
    }

    public static Adventurer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Adventurer instance has not been initialized!");
        }
        return instance;
    }

    public void receiveExperience(int experience) {
        this.currentExperience += experience;
        if (currentExperience >= requiredExperienceForNextLevel) {
            currentExperience -= requiredExperienceForNextLevel;
            this.expPointsToSpend = CombatUtil.getPointsBasedOnLevel(this.level);
            this.requiredExperienceForNextLevel = CombatUtil.getNextLevelExpRequirements(this.level);
            this.level++;
        }
    }

    public int getExpPointsToSpend() {
        return expPointsToSpend;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getRequiredExperienceForNextLevel() {
        return requiredExperienceForNextLevel;
    }

    public void increaseStat(AdventurerStat stat) {
        if (expPointsToSpend < 1) {
            return;
        }
        switch (stat) {
            case STRENGTH:
                super.strength += 2;
                double currentMax = maxHealth;
                maxHealth = strength * 50;
                currentHealth = currentHealth + (maxHealth - currentMax);
                break;
            case AGILITY:
                super.agility += 2;
                break;
            case SPEED:
                super.speed += (2 * 20);
                break;
        }
        expPointsToSpend--;
    }

    public void applyItemBonuses(Item item) {
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;
            this.baseDamage += weapon.getBonusDamage();
        }
        if (item instanceof Armor) {
            Armor armor = (Armor) item;
            this.armor += armor.getArmor();
        }
    }

    public void removeItemBonuses(Item item) {
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;
            this.baseDamage -= weapon.getBonusDamage();
        }
        if (item instanceof Armor) {
            Armor armor = (Armor) item;
            this.armor -= armor.getArmor();
        }
    }

    public boolean performRest() {
        Inventory inventory = Inventory.getInstance();
        if (inventory.getGold() < 10) {
            return false;
        }
        inventory.addGold(-10);
        heal();
        return true;
    }

    @Override
    public double getDamage() {
        return baseDamage;
    }

    @Override
    public int getDodgeActionColor() {
        return R.color.adventurer_dodge_color;
    }

    @Override
    public int getBlockActionColor() {
        return R.color.adventurer_block_color;
    }

    @Override
    public int getCritActionColor() {
        return R.color.adventurer_crit_color;
    }

    @Override
    public int getDamageActionColor() {
        return R.color.adventurer_damage_color;
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }


}
