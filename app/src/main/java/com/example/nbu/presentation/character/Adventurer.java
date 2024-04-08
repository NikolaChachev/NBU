package com.example.nbu.presentation.character;

public class Adventurer extends BaseCharacter {

    private int currentExperience;
    private int RequiredExperienceForNextLevel;

    public Adventurer(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
        currentExperience = 0;
        RequiredExperienceForNextLevel = 100;
    }


    @Override
    public void hitTarget(BaseCharacter target) {

    }
}
