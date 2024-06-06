package com.example.nbu.service.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.example.nbu.service.player.Adventurer;
import com.example.nbu.service.player.Inventory;
import com.google.gson.Gson;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NbuSharedPrefs {

    public static final String CHARACTER_SAVE_KEY = "character_key";

    public static final String INVENTORY_SAVE_KEY = "inventory_key";

    private static final String SHARED_PREFS_FILE = "com.example.nbu.service.saveFile";

    private final SharedPreferences sharedPrefs;

    @Inject
    NbuSharedPrefs(@ApplicationContext Context context) {
        sharedPrefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
    }

    public boolean saveGameProgress() {
        Gson gson = new Gson();
        Adventurer adventurer = Adventurer.getInstance();
        String data = gson.toJson(adventurer);
        Editor editor = sharedPrefs.edit();
        editor.putString(CHARACTER_SAVE_KEY, data);
        Inventory inventory = Inventory.getInstance();
        data = gson.toJson(inventory);
        editor.putString(INVENTORY_SAVE_KEY, data);
        editor.apply();
        return true;
    }

    public boolean containsSavedGame() {
        return sharedPrefs.contains(CHARACTER_SAVE_KEY) && sharedPrefs.contains(INVENTORY_SAVE_KEY);
    }

    public void loadGameProgress() {
        if (!containsSavedGame()) {
            return;
        }
        Gson gson = new Gson();
        Adventurer adventurer = gson.fromJson(sharedPrefs.getString(CHARACTER_SAVE_KEY, null), Adventurer.class);
        Adventurer.updateAdventurerInstance(adventurer);
        Inventory inventory = gson.fromJson(sharedPrefs.getString(INVENTORY_SAVE_KEY, null), Inventory.class);
        Inventory.loadInventory(inventory);
    }

    public void clearSaveFile() {
        sharedPrefs.edit().clear().apply();
    }


}