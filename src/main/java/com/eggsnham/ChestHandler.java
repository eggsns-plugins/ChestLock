package com.eggsnham;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ChestHandler implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if(block.getType() == Material.CHEST && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Chest chest = (Chest)block.getState();
            String currentUsers = chest.getCustomName();
            if(currentUsers == null) {
                chest.setCustomName("");
            }
            if(player.getInventory().getItemInMainHand().hasItemMeta()) {
                ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                if(Bukkit.getServer().getPlayer(im.getLore().get(0)) != null) {
                    Bukkit.getLogger().info("Adding player: " + Bukkit.getServer().getPlayer(im.getLore().get(0)).getName() + " to chest...");
                    if(currentUsers == null) {
                        Bukkit.getLogger().info("Current users is empty...");
                        chest.setCustomName(Bukkit.getServer().getPlayer(im.getLore().get(0)).getName());
                    }
                    else if(!currentUsers.contains(player.getName())) chest.setCustomName(currentUsers + "," + Bukkit.getServer().getPlayer(im.getLore().get(0)).getName());
                }
                chest.update();
                Bukkit.getLogger().info(currentUsers);
                if(currentUsers != null) player.sendMessage(ChatColor.GREEN + "User " + ChatColor.BLUE + im.getLore().get(0) + ChatColor.GREEN + " is now added to chest security");
                else player.sendMessage(ChatColor.RED + "Oops, an error occurred! " + ChatColor.BLUE + "You may need to try again\n\n" + ChatColor.RED + "ERROR CODE: CARD_UNREADABLE");
                event.setCancelled(true);
            } else {
                player.sendMessage(ChatColor.YELLOW + "Checking requirements...\n\n");
                if(currentUsers != null && !currentUsers.contains(player.getName()) && chest.getCustomName() != null) {
                    player.sendMessage(ChatColor.RED + "You cannot open that chest!");
                    event.setCancelled(true);
                } else {
                    player.sendMessage(ChatColor.GREEN + "Opening chest...");
                    event.setCancelled(false);
                }
            }
        }
    }
}
