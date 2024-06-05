package com.example.nbu.presentation.character;

import com.example.nbu.R;
import com.example.nbu.presentation.combat.CombatUtil;
import com.example.nbu.presentation.inventory.Inventory;
import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.Item;
import com.example.nbu.service.pojos.Weapon;

public class Adventurer extends BaseCharacter {

    private int currentExperience;
    private int requiredExperienceForNextLevel;

    private int expPointsToSpend = 0;

    private static Adventurer instance;

    private Adventurer(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
        currentExperience = 0;
        requiredExperienceForNextLevel = 100;
    }

    public static Adventurer initializeAdventurer(String name) {
        instance = new Adventurer(name, 1, 100.d, 0, 50, 2, 2, 5);
        return instance;
    }

    public static Adventurer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Adventurer instance has not been initialized!");
        }
        return instance;
    }

    public void receiveExperience(int experience){
        this.currentExperience+=experience;
        if(currentExperience >= requiredExperienceForNextLevel){
            currentExperience-=requiredExperienceForNextLevel;
            this.expPointsToSpend = CombatUtil.getPointsBasedOnLevel(this.level);
            this.requiredExperienceForNextLevel = CombatUtil.getNextLevelExpRequirements(this.level);
            this.level++;
        }
    }

    public int getExpPointsToSpend(){
        return expPointsToSpend;
    }

    public void increaseStat(AdventurerStat stat){
        if(expPointsToSpend < 1) return;
        switch (stat){
            case STRENGTH:
                super.strength+=2;
                double currentMax = maxHealth;
                maxHealth = strength * 50;
                currentHealth = currentHealth + (maxHealth - currentMax);
                break;
            case AGILITY:
                super.agility+=2;
                break;
            case SPEED:
                super.speed+=2;
                break;
        }
        expPointsToSpend--;
    }

    public void applyItemBonuses(Item item){
        if(item instanceof Weapon){
            Weapon weapon = (Weapon) item;
            this.baseDamage += weapon.getBonusDamage();
        }
        if(item instanceof Armor){
            Armor armor = (Armor) item;
            this.armor += armor.getArmor();
        }
    }

    public void removeItemBonuses(Item item){
        if(item instanceof Weapon){
            Weapon weapon = (Weapon) item;
            this.baseDamage -= weapon.getBonusDamage();
        }
        if(item instanceof Armor){
            Armor armor = (Armor) item;
            this.armor -= armor.getArmor();
        }
    }

    public boolean performRest(){
        Inventory inventory = Inventory.getInstance();
        if(inventory.getGold() < 10){
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
