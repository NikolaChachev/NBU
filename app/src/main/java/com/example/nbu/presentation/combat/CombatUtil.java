package com.example.nbu.presentation.combat;

import com.example.nbu.R;
import com.example.nbu.service.character.Enemy;
import com.example.nbu.service.pojos.Armor;
import com.example.nbu.service.pojos.ArmorTypes;
import com.example.nbu.service.pojos.Weapon;
import java.util.ArrayList;

public class CombatUtil {

    public static final ArrayList<Enemy> enemies = new ArrayList<>() {
        {
            add(new Enemy("Gazer", 1, 0, 50, 0, 2, 5, R.drawable.beast_4));
            add(new Enemy("Displacer beast", 2, 0, 100, 1, 10, 6, R.drawable.displacer_beast));
            add(new Enemy("Berbalang", 2, 15, 200, 2, 7, 5, R.drawable.berbalang));
            add(new Enemy("Animated armor", 2, 5, 100, 3, 10, 7, R.drawable.animated_armor));
            add(new Enemy("Aurochs", 3, 25, 150, 5, 10, 8, R.drawable.aurochs));
            add(new Enemy("Basilisk", 3, 10, 75, 8, 17, 10, R.drawable.basilisk));
            add(new Enemy("Bronze dragon wyrmling", 3, 0, 50, 10, 10, 20, R.drawable.bronze_dragon_wyrmling));
            add(new Enemy("Bugbear", 3, 0, 200, 7, 20, 15, R.drawable.beast_2));
            add(new Enemy("Carrion crawler", 4, 0, 200, 8, 15, 10, R.drawable.beast_2));
            add(new Enemy("Choldrith", 4, 15, 100, 10, 21, 15, R.drawable.beast_2));
            add(new Enemy("Death Dog", 4, 5, 50, 10, 15, 27, R.drawable.beast_2));
            add(new Enemy("Forlarren", 4, 25, 160, 11, 15, 10, R.drawable.beast_2));
            add(new Enemy("Ghast", 5, 30, 200, 12, 22, 25, R.drawable.beast_2));
            add(new Enemy("Glasswork Golem", 5, 45, 200, 7, 22, 17, R.drawable.beast_2));
            add(new Enemy("Grell", 5, 0, 200, 15, 11, 45, R.drawable.beast_2));
            add(new Enemy("Green Hag", 6, 20, 150, 13, 20, 22, R.drawable.beast_2));
            add(new Enemy("Barbed Devil", 6, 0, 150, 15, 40, 15, R.drawable.beast_2));
            add(new Enemy("Black Abishai", 6, 0, 175, 17, 50, 8, R.drawable.beast_2));
            add(new Enemy("Bodak", 7, 8, 25, 20, 15, 35, R.drawable.beast_2));
            add(new Enemy("Bulette", 7, 0, 25, 18, 12, 50, R.drawable.beast_2));
            add(new Enemy("Cambion", 7, 30, 125, 15, 20, 22, R.drawable.beast_2));
            add(new Enemy("Catoblepas", 8, 40, 285, 15, 20, 18, R.drawable.beast_2));
            add(new Enemy("vampire lord", 8, 10, 25, 22, 20, 40, R.drawable.beast_2));
            add(new Enemy("Chimera", 8, 25, 185, 15, 33, 23, R.drawable.beast_2));
            add(new Enemy("Draegloth", 9, 40, 200, 25, 50, 35, R.drawable.beast_2));
            add(new Enemy("Glabrezu", 9, 25, 75, 26, 25, 45, R.drawable.beast_2));
            add(new Enemy("Beholder", 9, 65, 275, 30, 25, 23, R.drawable.beast_2));
        }
    };

    public static final ArrayList<Weapon> weapons = new ArrayList<>() {
        {
            add(new Weapon("log", 5, 0, 4, 1));
            add(new Weapon("rusty dagger", 10, 2, 7, 1));
            add(new Weapon("kitchen knife", 20, 2, 10, 1));
            add(new Weapon("bat", 60, 4, 15, 2));
            add(new Weapon("ordinary dagger", 80, 6, 20, 2));
            add(new Weapon("military knife", 100, 6, 25, 2));
            add(new Weapon("scythe", 200, 8, 30, 3));
            add(new Weapon("small axe", 300, 8, 33, 3));
            add(new Weapon("short sword", 350, 10, 50, 3));
            add(new Weapon("axe", 500, 10, 100, 4));
            add(new Weapon("katana", 600, 12, 125, 4));
            add(new Weapon("mace", 725, 14, 145, 4));
            add(new Weapon("spear", 850, 16, 165, 4));
            add(new Weapon("poleaxe", 1000, 16, 195, 4));
            add(new Weapon("longsword", 1500, 18, 245, 5));
            add(new Weapon("double bladed axe", 2000, 20, 295, 5));
            add(new Weapon("warhammer", 2500, 24, 365, 5));
            add(new Weapon("great sword", 5000, 28, 445, 6));
            add(new Weapon("epic warmace", 8000, 30, 495, 6));
            add(new Weapon("heartseeker", 10000, 40, 645, 6));
        }
    };

    public static final ArrayList<Armor> armorSets = new ArrayList<>() {
        {
            add(new Armor("old helmet", 30, 1, 2, 1, ArmorTypes.HEAD));
            add(new Armor("old chest piece", 40, 1, 4, 1, ArmorTypes.CHEST));
            add(new Armor("old armguards", 15, 1, 1, 1, ArmorTypes.ARMS));
            add(new Armor("old pants", 30, 1, 2, 1, ArmorTypes.LEGS));
            add(new Armor("old boots", 15, 1, 1, 1, ArmorTypes.FEET));
            add(new Armor("leather helmet", 125, 4, 4, 2, ArmorTypes.HEAD));
            add(new Armor("leather breastplate", 250, 4, 7, 2, ArmorTypes.CHEST));
            add(new Armor("leather armguards", 80, 4, 3, 2, ArmorTypes.ARMS));
            add(new Armor("leather cuisse", 125, 4, 4, 2, ArmorTypes.LEGS));
            add(new Armor("leather boots", 40, 4, 2, 2, ArmorTypes.FEET));
            add(new Armor("bronze helmet", 1000, 10, 9, 3, ArmorTypes.HEAD));
            add(new Armor("bronze breastplate", 2500, 10, 15, 3, ArmorTypes.CHEST));
            add(new Armor("bronze armguards", 250, 10, 4, 3, ArmorTypes.ARMS));
            add(new Armor("bronze cuisse", 1000, 10, 9, 3, ArmorTypes.LEGS));
            add(new Armor("bronze boots", 80, 10, 3, 3, ArmorTypes.FEET));
            add(new Armor("silver helmet", 5000, 15, 12, 4, ArmorTypes.HEAD));
            add(new Armor("silver breastplate", 8500, 15, 25, 4, ArmorTypes.CHEST));
            add(new Armor("silver armguards", 2500, 15, 7, 4, ArmorTypes.ARMS));
            add(new Armor("silver cuisse", 5000, 15, 12, 4, ArmorTypes.LEGS));
            add(new Armor("silver boots", 250, 15, 4, 4, ArmorTypes.FEET));
            add(new Armor("golden helmet", 12500, 2, 25, 5, ArmorTypes.HEAD));
            add(new Armor("golden breastplate", 25000, 2, 35, 5, ArmorTypes.CHEST));
            add(new Armor("golden armguards", 10000, 2, 10, 5, ArmorTypes.ARMS));
            add(new Armor("golden cuisse", 12500, 2, 25, 5, ArmorTypes.LEGS));
            add(new Armor("golden boots", 1000, 2, 5, 5, ArmorTypes.FEET));
        }
    };

    public static int getPointsBasedOnLevel(int level) {
        int pointsToAdd = 2;
        switch (level) {
            case 1:
            case 9:
            case 17:
                pointsToAdd++;
                break;
            case 2:
                pointsToAdd += 3;
                break;
            case 3:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11:
            case 15:
            case 13:
            case 14:
            case 18:
                break;
            case 4:
            case 8:
            case 12:
            case 16:
            case 19:
                pointsToAdd += 2;
                break;
            default:
                if (level % 7 == 0) {
                    pointsToAdd += 3;
                }
                break;
        }
        return pointsToAdd;
    }

    public static int getNextLevelExpRequirements(int currentLevel) {
        switch (currentLevel) {
            case 1:
                return 500;
            case 8:
            case 4:
            case 6:
                return 2500;
            case 2:
            case 12:
            case 10:
            case 14:
                return 1000;
            case 3:
            case 5:
                return 1250;
            case 7:
            case 11:
            case 9:
                return 2000;
            case 13:
                return 3000;
            case 15:
                return 3500;
            case 16:
                return 4000;
            case 17:
            case 18:
                return 2700;
            default:
                return 2250;
        }
    }
}
