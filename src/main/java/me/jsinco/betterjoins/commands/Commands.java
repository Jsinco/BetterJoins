package me.jsinco.betterjoins.commands;

import me.jsinco.betterjoins.BetterJoins;
import me.jsinco.betterjoins.PlayerJoinLeave;
import me.jsinco.betterjoins.util.Messages_Effects;
import me.jsinco.betterjoins.util.PlayerJoinLeaveTest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        assert player != null;
        if (args.length > 0 && player.hasPermission("betterjoins.admin")){
            if (args[0].equalsIgnoreCase("reload")){
                BetterJoins.getPlugin(BetterJoins.class).reloadConfig();
                PlayerJoinLeave.reload();
                PlayerJoinLeaveTest.reload();
                Messages_Effects.reload();
                player.sendMessage("§aConfig reloaded");

            } else if (args[0].equalsIgnoreCase("join")) {
                PlayerJoinLeaveTest.TestPlayerJoin(player.getName());

            } else if (args[0].equalsIgnoreCase("quit")) {
                PlayerJoinLeaveTest.TestPlayerQuit(player.getName());

            } else if (args[0].equalsIgnoreCase("firstjoin")) {
                PlayerJoinLeaveTest.TestPlayerFirstJoin(player.getName());

            }
            return true;
        } else {
            player.sendMessage("§c/betterjoins <reload|join|quit|firstjoin> (Permission: betterjoins.admin)");
            player.sendMessage("§c/joinsounds - toggle sounds when joining or leaving");
        }
        return true;
    }
}
