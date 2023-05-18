package me.jsinco.betterjoins.util;

import me.jsinco.betterjoins.BetterJoins;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Messages_Effects {
    static FileConfiguration config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    public static void reload (){
        config = BetterJoins.getPlugin(BetterJoins.class).getConfig();
    }


    public void sendTitle(Player player) {
        List <String> TitleMsg = config.getStringList("TitleMsg");
        String string1 = ColorUtils.translateColorCodes(TitleMsg.get(0).replace("%player%", player.getName()));
        String string2 = ColorUtils.translateColorCodes(TitleMsg.get(1).replace("%player%", player.getName()));
        player.sendTitle(string1, string2, config.getInt("FadeIn"), config.getInt("Stays"), config.getInt("FadeOut"));
    }

    public void sendParticleEffect(Player player){
        player.getWorld().spawnParticle(Particle.valueOf(config.getString("ParticleType")),
                player.getLocation(), config.getInt("ParticleAmount"), 0.5, 0.5, 0.5, 0.01);
        //joining player cannot see particles spawned by world, latter is for observing players
        player.spawnParticle(Particle.valueOf(config.getString("ParticleType")),
                player.getLocation(), config.getInt("ParticleAmount"), 0.5, 0.5, 0.5, 0.01);
    }

    public void sendSound(Player player, String sounds) {
        if (sounds.equalsIgnoreCase("player")){
            player.playSound(player.getLocation(), Sound.valueOf(config.getString("JoinSound")), config.getInt("Volume"), config.getInt("Pitch"));
        } else if (sounds.equalsIgnoreCase("all")){
            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.getScoreboardTags().contains("betterjoins.silent")) return;
                p.playSound(p.getLocation(), Sound.valueOf(config.getString("JoinSound")), config.getInt("Volume"), config.getInt("Pitch"));
            });
        }
    }

    public void sendActionBar(Player player){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorUtils.translateColorCodes(config.getString("JoinBarMsg").replace("%player%", player.getName()))));

    }

    public void sendFirstJoinMsg(Player player, List<String> firstJoinMsg, boolean test) {
        for (String msg : firstJoinMsg) {
            if (test) {
                player.sendMessage(ColorUtils.translateColorCodes(msg).replace("%player%", player.getName()).replace("%joins%", String.valueOf(config.getInt("UniqueJoins"))).replace("[","").replace("]",""));

            } else {
                Bukkit.broadcastMessage(ColorUtils.translateColorCodes(msg).replace("%player%", player.getName()).replace("%joins%", String.valueOf(config.getInt("UniqueJoins"))).replace("[","").replace("]",""));

            }

        }
    }


    //leave sfx
    public void sendLeaveSound() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (p.getScoreboardTags().contains("betterjoins.silent")) return;
            p.playSound(p.getLocation(), Sound.valueOf(config.getString("QuitSound")), config.getInt("Volume"), config.getInt("Pitch"));
        });
    }
}
