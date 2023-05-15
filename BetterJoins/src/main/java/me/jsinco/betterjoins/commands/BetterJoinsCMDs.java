package me.jsinco.betterjoins.commands;

import me.jsinco.betterjoins.BetterJoins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterJoinsCMDs implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("JoinBarMsgText")) {
               StringBuilder msg = new StringBuilder();
               for (int i = 1; i < args.length; i++) {
                   msg.append(args[i]);
               }
                BetterJoins.getPlugin(BetterJoins.class).getConfig().set("JoinBarMsgText", msg.toString());
            } //else if () {

           // }
        }

        return true;
    }
}
