package com.eggsnham.ChestLock;

import com.eggsnham.ChestLock.Lib.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class onChestOpen implements Listener {
    Plugin plugin;
    Boolean debug;
    FileConfig fileConfig;

    public onChestOpen(Plugin plugin)
    {
        this.plugin = plugin;
        this.fileConfig = new FileConfig(plugin);
        this.debug = Boolean.getBoolean(fileConfig.readFromYml(new File(plugin.getDataFolder(), "config.yml"), "development.debug"));
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        // Check if action was a right click or left click
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();

            // Make sure that the block is a chest or barrel
            if (block.getType() == Material.CHEST || block.getType() == Material.BARREL) {
                String loc = block.getLocation().getX() + ", " + block.getLocation().getY() + ", " + block.getLocation().getZ();
                // Load the user's data file with all their chests
                List<String> arr = fileConfig.readFromYmlList(new File(plugin.getDataFolder(), player.getName() + "/userdata.yml"), "chests");
                String[] chests;
                chests = arr.toArray(new String[arr.size()]);

                ArrayList<Boolean> bools = new ArrayList<>();
                for (String s : chests) {
                    if (s.equals(loc)) {
                        bools.add(true);
                    } else {
                        bools.add(false);
                    }
                }
                if (!bools.contains(true) && !player.isOp()) {
                    player.sendMessage(ChatColor.RED + "Not your chest");
                    event.setCancelled(true);
                } else {
                    if (event.getAction() == Action.LEFT_CLICK_BLOCK && !loc.equals("0.0, 0.0, 0.0")) {
                        arr.remove(loc);
                        fileConfig.writeToYml(new File(plugin.getDataFolder(), player.getName() + "/userdata.yml"), "chests", arr);
                    } else if(loc.equals("0.0, 0.0, 0.0") && event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
