package com.example.nbu.presentation.combat;

import com.example.nbu.presentation.character.Enemy;
import java.util.ArrayList;

public class Util {

    public static final ArrayList<Enemy> enemies = new ArrayList<>() {
        {
            add(new Enemy("ferral rabbit", 1, 20.d, 0, 100, 2, 5, 8));
            add(new Enemy("wolf", 2, 50.d, 0, 200, 15, 10, 10));
            add(new Enemy("bear", 2, 100.d, 15, 400, 7, 7, 5));
            add(new Enemy("wild boar", 2, 75.d, 5, 200, 5, 10, 8));
            add(new Enemy("weakened deserter", 3, 120.d, 25, 300, 5, 10, 8));
            add(new Enemy("bandit", 3, 175.d, 10, 150, 10, 17, 10));
            add(new Enemy("pirate", 3, 100.d, 0, 100, 25, 10, 20));
            add(new Enemy("dothraki", 3, 150.d, 0, 400, 25, 20, 15));
            add(new Enemy("zombie", 4, 400.d, 0, 400, 15, 15, 10));
            add(new Enemy("bandit leader", 4, 250.d, 15, 200, 10, 21, 15));
            add(new Enemy("pirate captain", 4, 150.d, 5, 100, 35, 15, 27));
            add(new Enemy("deserted soldier", 4, 260.d, 25, 380, 10, 15, 10));
            add(new Enemy("army captain", 5, 350.d, 30, 400, 12, 22, 25));
            add(new Enemy("mercenary", 5, 300.d, 45, 400, 13, 22, 17));
            add(new Enemy("headhunter", 5, 300.d, 0, 400, 50, 11, 45));
            add(new Enemy("goblin", 6, 550.d, 20, 300, 20, 20, 22));
            add(new Enemy("cyclopse", 6, 500.d, 0, 300, 2, 40, 15));
            add(new Enemy("troll", 6, 700.d, 0, 250, 2, 50, 8));
            add(new Enemy("vampire", 7, 650.d, 8, 50, 40, 15, 35));
            add(new Enemy("hydralisk", 7, 700.d, 0, 50, 37, 12, 50));
            add(new Enemy("undead soldier", 7, 800.d, 30, 250, 15, 20, 22));
            add(new Enemy("abomination", 8, 1000.d, 40, 470, 4, 20, 18));
            add(new Enemy("vampire lord", 8, 750.d, 10, 50, 40, 20, 40));
            add(new Enemy("demons` servant", 8, 580.d, 25, 270, 15, 33, 23));
            add(new Enemy("fallen angel", 9, 1500.d, 40, 400, 20, 50, 35));
            add(new Enemy("hellhound", 9, 1000.d, 25, 150, 15, 25, 45));
            add(new Enemy("unltralisk", 9, 2000.d, 65, 550, 2, 25, 23));
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
                break;
            case 4:
            case 8:
            case 12:
            case 16:
            case 19:
                pointsToAdd += 2;
                break;
            case 18:
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
            case 8:
            case 4:
            case 6:
                return 1500;
            case 2:
            case 12:
            case 10:
            case 14:
                return 2500;
            case 3:
            case 5:
                return 1000;
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
