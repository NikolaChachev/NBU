package com.example.nbu.presentation.combat;

import com.example.nbu.R;
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.character.Enemy;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CombatViewModel extends AbstractViewModel {
    private static final int ACTION_TIMER_STARTING_VALUE = 100;

    private final Adventurer adventurer;
    private Enemy enemy;

    private final List<CombatLog> combatLogs;
    private int adventurerTimer = ACTION_TIMER_STARTING_VALUE;
    private int enemyTimer = ACTION_TIMER_STARTING_VALUE;

    private int currentRound = 1;

    @Inject
    public CombatViewModel(ACoroutineContextProvider provider) {
        super(provider);
        adventurer = Adventurer.getInstance();
        combatLogs = new LinkedList<>();
        generateEnemy();
    }

    private void generateEnemy() {
        enemy = new Enemy("rabbit", 1, 10.d, 0, 10, 1, 3, 5);
        //todo this will be the logic, where we will generate an enemy we encounter
    }

    public String getEncounterText() {
        return "You have encountered: " + enemy.toString();
    }

    public void runCombatUntilEnd() {
        while (!adventurer.isDead() && !enemy.isDead()) {
            tickCombatTime();
        }
    }

    public void runNextCombatRound() {
        tickCombatTime();
    }

    private void tickCombatTime() {
        combatLogs.add(new CombatLog(currentRound + " round started", R.color.black));
        while (adventurerTimer > 0) {
            adventurerTimer -= adventurer.getSpeed();
            enemyTimer -= enemy.getSpeed();
        }
        String log;
        while (adventurerTimer <= 0) {
            adventurerTimer += ACTION_TIMER_STARTING_VALUE;
            log = adventurer.getName() + " attacks!";
            combatLogs.add(new CombatLog(log, R.color.black));
            Random rn = new Random();
            int value = rn.nextInt() % 101;
            if (value < enemy.getAgility()) {
                log = enemy.getName() + " dodged the attack!";
                combatLogs.add(new CombatLog(log, R.color.blue));
            } else {
                value = rn.nextInt(101);
                double damageDone = adventurer.getDamage();
                if (value <= enemy.getStrength()) {
                    damageDone *= 0.3;
                    log = enemy.getName() + " blocks the attack!";
                    combatLogs.add(new CombatLog(log, R.color.blue));
                } else {
                    value = rn.nextInt(101);
                    if (value <= adventurer.getAgility()) {
                        log = adventurer.getName() + " critically strikes!";
                        combatLogs.add(new CombatLog(log, R.color.green));
                        damageDone *= 2;
                    }
                }
                if (enemy.getArmor() > 0) {
                    double damageReduction = (double) enemy.getArmor() / 100;
                    damageDone = damageDone - damageReduction * damageDone;
                }
                log = adventurer.getName() + " deals " + damageDone + " damage to " + enemy.getName();
                combatLogs.add(new CombatLog(log, R.color.yellow));
                enemy.takeDamage(damageDone);
                if (enemy.isDead()) {
                    log = enemy.getName() + " is dead! ";
                    combatLogs.add(new CombatLog(log, R.color.green));
//                    int expPoints = enemy.getLevel() * 50;

//                    adventurer.getExp(expPoints);
                    //adventurer.addLoot(enemy)
                    break;
                }
                log = enemy.getName() + " has " + enemy.getCurrentHealth() + " health left";
                combatLogs.add(new CombatLog(log, R.color.black));
            }
            System.out.println("-----------------------------------------");
        }
        adventurerTimer = ACTION_TIMER_STARTING_VALUE;
        while (enemyTimer <= 0) {
            enemyTimer += ACTION_TIMER_STARTING_VALUE;
        }
        ++currentRound;
    }

    public List<CombatLog> getLogs() {
        List<CombatLog> copy = new LinkedList<>(combatLogs);
        combatLogs.clear();
        return copy;
    }


}
