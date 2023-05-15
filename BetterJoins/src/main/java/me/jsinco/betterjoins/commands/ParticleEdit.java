package me.jsinco.betterjoins.commands;

import me.jsinco.betterjoins.util.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParticleEdit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player)sender;

        if (args.length > 0) {
            player.sendMessage(ColorUtils.translateColorCodes(args[0]));
        }

        return true;
    }
}
