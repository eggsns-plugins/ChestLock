package com.eggsnham.ChestLock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CreateLocked implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            ItemStack BPlanks = new ItemStack(Material.BIRCH_PLANKS);
            ItemStack OPlanks = new ItemStack(Material.OAK_PLANKS);
            ItemStack DOPlanks = new ItemStack(Material.DARK_OAK_PLANKS);
            ItemStack WPlanks = new ItemStack(Material.WARPED_PLANKS);
            ItemStack CPlanks = new ItemStack(Material.CRIMSON_PLANKS);
            ItemStack APlanks = new ItemStack(Material.ACACIA_PLANKS);
            ItemStack SPlanks = new ItemStack(Material.SPRUCE_PLANKS);
            ItemStack JPlanks = new ItemStack(Material.JUNGLE_PLANKS);
            ItemStack MPlanks = new ItemStack(Material.MANGROVE_PLANKS);
            Inventory inv = player.getInventory();
            boolean hasPlanks = false;
            String plankType = null;

            if(inv.containsAtLeast(BPlanks, 8)) {
                hasPlanks = true;
                plankType = BPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(MPlanks, 8)) {
                hasPlanks = true;
                plankType = MPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(OPlanks, 8)) {
                hasPlanks = true;
                plankType = OPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(DOPlanks, 8)) {
                hasPlanks = true;
                plankType = DOPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(WPlanks, 8)) {
                hasPlanks = true;
                plankType = WPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(CPlanks, 8)) {
                hasPlanks = true;
                plankType = CPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(APlanks, 8)) {
                hasPlanks = true;
                plankType = APlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(SPlanks, 8)) {
                hasPlanks = true;
                plankType = SPlanks.getType().toString().toLowerCase();
            }
            else if(inv.containsAtLeast(JPlanks, 8)) {
                hasPlanks = true;
                plankType = JPlanks.getType().toString().toLowerCase();
            }

            if(args[0].equals("chest") && hasPlanks) {
                ItemStack lockedChest = new ItemStack(Material.CHEST, 1);
                ItemMeta lcIm = lockedChest.getItemMeta();

                lcIm.setDisplayName("Locked");
                lockedChest.setItemMeta(lcIm);

                inv.remove(new ItemStack(Material.getMaterial(plankType.toUpperCase()), 8));
                inv.addItem(lockedChest);
                player.sendMessage(ChatColor.GREEN + "Created chest!");
            }
            else if (args[0].equals("chest") && !hasPlanks)
            {
                player.sendMessage(ChatColor.RED + "You do not have at 8+ wood planks for making a chest!");
            }
            else if(args[0].equals("barrel")) {
                player.sendMessage(ChatColor.DARK_BLUE + "Sorry, barrels are still in development");
            }
            return true;
        }
        return false;
    }
}
