package com.eggsnham;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class setLore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player)sender;
        ArrayList<String> lore = new ArrayList<>();

        for(String s : args) {
            lore.add(s);
        }

        ItemStack stack = player.getInventory().getItemInMainHand();
        ItemMeta im = stack.getItemMeta();

        im.setLore(lore);
        im.setDisplayName("Security Card");

        stack.setItemMeta(im);

        player.sendMessage(ChatColor.GREEN + "Done!");

        return true;
    }
}
