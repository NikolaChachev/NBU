package com.example.nbu.service.character;

import com.example.nbu.R;
import com.example.nbu.service.pojos.Item;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends BaseCharacter implements ILootable {

    private int drawableSource;

    public Enemy(String name, int level, int armor, int speed, int strength, int agility, double baseDamage, int imageSource) {
        super(name, level, armor, speed, strength, agility, baseDamage);
        this.drawableSource = imageSource;
    }

    public int getDrawableSource() {
        return drawableSource;
    }

    @Override
    public void hitTarget(BaseCharacter target) {

    }

    @Override
    public double getDamage() {
        return baseDamage;
    }

    @Override
    public int getDodgeActionColor() {
        return R.color.enemy_dodge_color;
    }

    @Override
    public int getBlockActionColor() {
        return R.color.enemy_block_color;
    }

    @Override
    public int getCritActionColor() {
        return R.color.enemy_crit_color;
    }

    @Override
    public int getDamageActionColor() {
        return R.color.enemy_damage_color;
    }

    @Override
    public String getLoot() {
        return level * 10  + " gold\n" + level * 50 + " experience";
    }

    public static List<Item> generateLootBasedOnEnemy(String name, int level){
        ArrayList<Item> items =  new ArrayList<>();
        items.add(new Item("Cloth", 10));
        items.add(new Item("Bones", 100));
        return items;
    }
}


