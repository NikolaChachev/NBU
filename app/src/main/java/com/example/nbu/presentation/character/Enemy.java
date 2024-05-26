package com.example.nbu.presentation.character;

import com.example.nbu.service.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends BaseCharacter implements ILootable {

    public Enemy(String name, int level, Double maxHealth, int armor, int speed, int strength, int agility, double baseDamage) {
        super(name, level, maxHealth, armor, speed, strength, agility, baseDamage);
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }

    @Override
    public double getDamage() {
        return baseDamage;
    }

    @Override
    public List<Item> getLoot() {
        return generateLootBasedOnEnemy(getName(), getLevel());
    }

    public static List<Item> generateLootBasedOnEnemy(String name, int level){
        ArrayList<Item> items =  new ArrayList<>();
        items.add(new Item("Cloth", 10));
        items.add(new Item("Bones", 100));
        return items;
    }
}


