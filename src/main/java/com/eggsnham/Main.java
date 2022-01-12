package com.eggsnham;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(this.getName() + " has started!");

        getCommand("usercard").setExecutor(new setLore());
        getCommand("usercard").setTabCompleter(new setLoreTab());

        Bukkit.getServer().getPluginManager().registerEvents(new ChestHandler(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(this.getName() + " is stopping...");
    }
}
