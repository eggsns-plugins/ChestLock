package com.eggsnham.ChestLock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CreateLockedTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1) {
            List<String> options = new ArrayList<String>();
            options.add("chest");
            options.add("barrel");
            return options;
        }
        return null;
    }
}
