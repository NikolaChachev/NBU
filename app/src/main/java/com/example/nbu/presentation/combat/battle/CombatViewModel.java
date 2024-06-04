package com.example.nbu.presentation.combat.battle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.nbu.R;
import com.example.nbu.mvvm.AbstractViewModel;
import com.example.nbu.presentation.character.Adventurer;
import com.example.nbu.presentation.character.BaseCharacter;
import com.example.nbu.presentation.character.Enemy;
import com.example.nbu.presentation.combat.CombatUtil;
import com.example.nbu.presentation.combat.summary.SummaryFragment;
import com.example.nbu.presentation.inventory.Inventory;
import com.example.nbu.service.coroutines.ACoroutineContextProvider;
import com.example.nbu.service.pojos.Item;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;

@HiltViewModel
public class CombatViewModel extends AbstractViewModel {

    private static final int ACTION_TIMER_STARTING_VALUE = 100;

    private static final String ENCOUNTER_PREFIX = "You have encountered: ";

    private final Adventurer adventurer;

    private Enemy enemy;

    private final List<CombatLog> combatLogs;

    private int adventurerTimer = ACTION_TIMER_STARTING_VALUE;

    private int enemyTimer = ACTION_TIMER_STARTING_VALUE;

    private int currentRound = 1;

    private final MutableLiveData<CombatStatus> combatStatus = new MutableLiveData<>(CombatStatus.ENCOUNTER);

    public final LiveData<CombatStatus> _combatStatus = combatStatus;

    private final MutableLiveData<String> _encounterText = new MutableLiveData<>();

    public LiveData<String> encounterText = _encounterText;

    private int expReward = 0;

    private int goldReward = 0;

    @Inject
    public CombatViewModel(ACoroutineContextProvider provider) {
        super(provider);
        adventurer = Adventurer.getInstance();
        combatLogs = new LinkedList<>();
    }

    @Nullable
    @Override
    public Bundle postNavigationArgs() {
        Bundle bundle = new Bundle();
        bundle.putInt(SummaryFragment.GOLD_EARNED, goldReward);
        bundle.putInt(SummaryFragment.EXP_EARNED, expReward);
        return bundle;
    }

    /**
     * Generates enemy based on the player current level. It will always generate a random
     * enemy in the range of [X,Y], where X = player level - 1 and Y = player level + 1
     * Once the player reaches level 18 there will be a small chance that he encounters the
     * final boss, which will increase every time they level up.
     * At level 21 and above the chance to encounter the final boss is using the following formula:
     * ( (Character level - 20) * 5) + 20 i.e. 25% at level 21, 30% at level 22 and so on.
     */
    private void generateEnemy() {
        Random rn = new Random();
        int enemyIndex;
        switch (adventurer.getLevel()) {
            case 1:
                enemyIndex = 0;
                break;
            case 2:
                enemyIndex = rn.nextInt(4);
                break;
            case 3:
                enemyIndex = rn.nextInt(7) + 1;
                break;
            case 4:
            case 5:
            case 6:
                enemyIndex = rn.nextInt(8) + 4;
                break;
            case 7:
            case 8:
            case 9:
                enemyIndex = rn.nextInt(7) + 8;
                break;
            case 10:
            case 11:
            case 12:
                enemyIndex = rn.nextInt(6) + 12;
                break;
            case 13:
            case 14:
            case 15:
                enemyIndex = rn.nextInt(6) + 15;
                break;
            case 16:
            case 17:
                enemyIndex = rn.nextInt(6) + 18;
                break;
            default:
                enemyIndex = rn.nextInt(6) + 21;
        }
        enemy = CombatUtil.enemies.get(enemyIndex);
        enemy.heal();
        _encounterText.postValue(ENCOUNTER_PREFIX + enemy.getName());
        combatStatus.postValue(CombatStatus.IN_PROGRESS);
    }

    public String getEnemyDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(enemy.toString());
        sb.append("\n\nPossible loot:\n");
        for (Item item : enemy.getLoot()) {
            sb.append(item.getName()).append(", worth ").append(item.buyPrice()).append("g\n");
        }
        return sb.toString();
    }

    public void refreshEnemy() {
        generateEnemy();
    }

    public void attemptToFlee() {
        if (isFleeingSuccessful()) {
            combatStatus.postValue(CombatStatus.SUCCESSFUL_FLEE);
        } else {
            combatStatus.postValue(CombatStatus.FAILED_FLEE);
        }
    }

    private boolean isFleeingSuccessful() {
        int escapeChance = (adventurer.getAgility() - enemy.getAgility()) * 2;
        if (escapeChance <= 0) {
            return false;
        }
        Random rn = new Random();
        return rn.nextInt(101) < escapeChance;
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
        while (adventurerTimer > 0 || enemyTimer > 0) {
            adventurerTimer -= adventurer.getSpeed();
            enemyTimer -= enemy.getSpeed();
        }
        String log;
        while (adventurerTimer <= 0) {
            adventurerTimer += ACTION_TIMER_STARTING_VALUE;
            emulateAttack(adventurer, enemy);
            if (enemy.isDead()) {
                log = enemy.getName() + " is dead! ";
                combatLogs.add(new CombatLog(log, R.color.green));
                combatStatus.postValue(CombatStatus.VICTORY);
                expReward = enemy.getLevel() * 50;
                goldReward = enemy.getLevel() * 10;
                adventurer.receiveExperience(expReward);
                Inventory.getInstance().addGold(goldReward);
                //todo add a loot generator that will give random loot
                combatStatus.postValue(CombatStatus.VICTORY);
                return;
            }
            log = enemy.getName() + " has " + enemy.getCurrentHealth() + " health left";
            combatLogs.add(new CombatLog(log, R.color.black));
        }

        adventurerTimer = ACTION_TIMER_STARTING_VALUE;
        while (enemyTimer <= 0) {
            enemyTimer += ACTION_TIMER_STARTING_VALUE;
            emulateAttack(enemy, adventurer);
            if (adventurer.isDead()) {
                combatStatus.postValue(CombatStatus.DEFEAT);
                return;
            }
        }
        enemyTimer = ACTION_TIMER_STARTING_VALUE;
        ++currentRound;
        log = "-----------------------------------------";
        combatLogs.add(new CombatLog(log, R.color.black));
    }

    private void emulateAttack(BaseCharacter attacker, BaseCharacter defender) {
        String log;
        log = attacker.getName() + " attacks!";
        combatLogs.add(new CombatLog(log, R.color.black));
        Random rn = new Random();
        int value = rn.nextInt() % 101;
        if (value < defender.getAgility()) {
            log = defender.getName() + " dodged the attack!";
            combatLogs.add(new CombatLog(log, defender.getDodgeActionColor()));
            return;
        }

        value = rn.nextInt(101);
        double damageDone = attacker.getDamage();
        if (value <= defender.getStrength()) {
            damageDone *= 0.3;
            log = defender.getName() + " blocks the attack!";
            combatLogs.add(new CombatLog(log, defender.getBlockActionColor()));
        } else {
            value = rn.nextInt(101);
            if (value <= attacker.getAgility()) {
                log = attacker.getName() + " critically strikes!";
                combatLogs.add(new CombatLog(log, attacker.getCritActionColor()));
                damageDone *= 2;
            }
        }
        if (defender.getArmor() > 0) {
            double damageReduction = (double) defender.getArmor() / 100;
            damageDone = damageDone - damageReduction * damageDone;
        }
        log = attacker.getName() + " deals " + damageDone + " damage to " + defender.getName();
        combatLogs.add(new CombatLog(log, attacker.getDamageActionColor()));
        defender.takeDamage(damageDone);
    }

    public List<CombatLog> getLogs() {
        List<CombatLog> copy = new LinkedList<>(combatLogs);
        combatLogs.clear();
        return copy;
    }
}
