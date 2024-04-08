package com.example.nbu.presentation.character;

public class Enemy extends BaseCharacter{

    public Enemy(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }
}
