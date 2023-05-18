package me.jsinco.betterjoins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleJoinSounds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        assert player != null;

        if (player.getScoreboardTags().contains("betterjoins.silent")) {
            player.sendMessage("§aJoin sounds enabled");
            player.removeScoreboardTag("betterjoins.silent");
        } else {
            player.sendMessage("§cJoin sounds disabled");
            player.addScoreboardTag("betterjoins.silent");
        }


        return true;
    }
}
