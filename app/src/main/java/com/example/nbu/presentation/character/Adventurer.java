package com.example.nbu.presentation.character;

import com.example.nbu.presentation.combat.Util;

public class Adventurer extends BaseCharacter {

    private int currentExperience;
    private int requiredExperienceForNextLevel;

    private int expPointsToSpend = 10;

    private static Adventurer instance;

    private Adventurer(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
        currentExperience = 0;
        requiredExperienceForNextLevel = 100;
    }

    public static void initializeAdventurer(String name) {
        instance = new Adventurer(name, 1, 12000.d, 0, 50, 1, 100, 5);
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
            this.expPointsToSpend = Util.getPointsBasedOnLevel(this.level);
            this.requiredExperienceForNextLevel = Util.getNextLevelExpRequirements(this.level);
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
                super.strength++;
                break;
            case AGILITY:
                super.agility++;
                break;
            case SPEED:
                super.speed++;
                break;
        }
        expPointsToSpend--;
    }

    @Override
    public double getDamage() {
        return baseDamage;
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }
}
