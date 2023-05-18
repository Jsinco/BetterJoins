package me.jsinco.betterjoins.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("betterjoins") && args.length == 1){
            return List.of("reload", "join", "quit", "firstjoin");
        }
        return null;
    }
}
