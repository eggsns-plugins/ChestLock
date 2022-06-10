package com.eggsnham.ChestLock;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class onPlayerJoin implements Listener  {
    Plugin plugin;

    public onPlayerJoin(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        File file = new File(plugin.getDataFolder(), event.getPlayer().getName() + "/userdata.yml");
        File folder = new File(plugin.getDataFolder(), event.getPlayer().getName() + "/");

        if(!folder.exists()) {
            folder.mkdir();
        }

        try {
            if(!file.exists()) {
                ArrayList<String> chests = new ArrayList<String>();
                chests.add("0.0, 0.0, 0.0");
                file.createNewFile();
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("chests", chests);
                config.save(file);
            }
        } catch(IOException ex) {
            plugin.getLogger().log(Level.SEVERE, String.valueOf(ex));
        }
    }
}
