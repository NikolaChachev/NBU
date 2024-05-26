package com.example.nbu.presentation.character;

public class Adventurer extends BaseCharacter {

    private int currentExperience;
    private int RequiredExperienceForNextLevel;

    private static Adventurer instance;

    private Adventurer(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
        currentExperience = 0;
        RequiredExperienceForNextLevel = 100;
    }

    public static void initializeAdventurer(String name) {
        instance = new Adventurer(name, 1, 120.d, 0, 50, 1, 100, 5);
    }

    public static Adventurer getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Adventurer instance has not been initialized!");
        }
        return instance;
    }

    @Override
    public double getDamage() {
        return baseDamage;
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }
}
