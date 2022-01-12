package com.eggsnham;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class setLoreTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        if(args.length == 1) {
            list.clear();
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                list.add(player.getName());
            });
            return list;
        } else if(args.length > 1) {
            return null;
        }
        return null;
    }
}
