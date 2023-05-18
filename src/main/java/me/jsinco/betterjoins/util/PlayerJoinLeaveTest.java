package me.jsinco.betterjoins.util;

import me.jsinco.betterjoins.BetterJoins;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class PlayerJoinLeaveTest {
    static FileConfiguration config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    public static void reload (){
        config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    }
    static Messages_Effects messagesEffects = new Messages_Effects();

    public static void TestPlayerFirstJoin(String playerName){
        Player player = Bukkit.getPlayerExact(playerName);

        List<String> firstJoinMsgs = config.getStringList("FirstJoin");
        messagesEffects.sendFirstJoinMsg(player, firstJoinMsgs);
    }

    public static void TestPlayerJoin(String playerName){
        Player player = Bukkit.getPlayerExact(playerName);
        String sounds = config.getString("Sounds");
        String particles = config.getString("ParticleType");

        String prefix = config.getString("JoinPrefix");

        List<String> AJoinMsg = config.getStringList("JoinMsg");
        int random = new Random().nextInt(AJoinMsg.size());

        if (prefix!= null) {
            player.sendMessage(ColorUtils.translateColorCodes(prefix) + ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
        } else {
            player.sendMessage(ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
        }


        messagesEffects.sendTitle(player);
        messagesEffects.sendActionBar(player);

        if (particles != null) messagesEffects.sendParticleEffect(player);
        if (sounds != null) player.playSound(player.getLocation(), Sound.valueOf(config.getString("JoinSound")), config.getInt("Volume"), config.getInt("Pitch"));
    }

    public static void TestPlayerQuit(String playerName){
        Player player = Bukkit.getPlayerExact(playerName);

        String prefix = config.getString("QuitPrefix");

        List<String> AJoinMsg = config.getStringList("QuitMsg");
        int random = new Random().nextInt(AJoinMsg.size());

        if (prefix!= null) {
            player.sendMessage(ColorUtils.translateColorCodes(prefix) + ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));

        } else {
            player.sendMessage(ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
        }

        if (config.getString("QuitSound") != null) player.playSound(player.getLocation(), Sound.valueOf(config.getString("QuitSound")), config.getInt("Volume"), config.getInt("Pitch"));
    }
}
