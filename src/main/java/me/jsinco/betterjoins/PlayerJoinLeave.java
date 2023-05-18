package me.jsinco.betterjoins;

import me.jsinco.betterjoins.util.ColorUtils;
import me.jsinco.betterjoins.util.Messages_Effects;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Random;

public class PlayerJoinLeave implements Listener {
    static FileConfiguration config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    public static void reload (){
        config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    }
    Messages_Effects messagesEffects = new Messages_Effects();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String sounds = config.getString("Sounds");
        String particles = config.getString("ParticleType");
        boolean UseFirstJoinMsg = config.getBoolean("UseFirstJoin?");
        boolean UseTitle = config.getBoolean("UseTitle?");
        boolean UseActionBar = config.getBoolean("UseActionBar?");

        if (UseTitle) messagesEffects.sendTitle(player);
        if (UseActionBar) messagesEffects.sendActionBar(player);
        if (particles != null) messagesEffects.sendParticleEffect(player);
        if (sounds != null) messagesEffects.sendSound(player, sounds);

        if (!player.hasPlayedBefore() && UseFirstJoinMsg){
            event.setJoinMessage(null); //disable default join message

            List<String> firstJoinMsgs = config.getStringList("FirstJoin");
            messagesEffects.sendFirstJoinMsg(player, firstJoinMsgs);

            config.set("UniqueJoins", config.getInt("UniqueJoins") + 1); //count up
        } else { // normal join
            String prefix = config.getString("JoinPrefix");

            List<String> AJoinMsg = config.getStringList("JoinMsg");
            int random = new Random().nextInt(AJoinMsg.size());

            if (prefix!= null) {
                event.setJoinMessage(ColorUtils.translateColorCodes(prefix) + ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));

            } else {
                event.setJoinMessage(ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
            }

        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String prefix = config.getString("QuitPrefix");

        List<String> AJoinMsg = config.getStringList("QuitMsg");
        int random = new Random().nextInt(AJoinMsg.size());

        if (prefix != null) {
            event.setQuitMessage(ColorUtils.translateColorCodes(prefix) + ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
        } else {
            event.setQuitMessage(ColorUtils.translateColorCodes(AJoinMsg.get(random).replace("%player%", player.getName())));
        }

        if (config.getString("QuitSound") != null) messagesEffects.sendLeaveSound();
    }
}
