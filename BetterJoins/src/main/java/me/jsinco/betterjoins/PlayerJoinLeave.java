package me.jsinco.betterjoins;

import me.jsinco.betterjoins.util.ColorUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerJoinLeave implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        boolean UseFirstJoinMsg = BetterJoins.getPlugin(BetterJoins.class).getConfig().getBoolean("ShouldUseFirstJoin?");

        Player player = event.getPlayer();


        //action bar
        String JoinBarMsgText = BetterJoins.getPlugin(BetterJoins.class).getConfig().getString("JoinBarMsgText");
        String finalBarMsg = JoinBarMsgText.replace("%player%", player.getName());
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorUtils.translateColorCodes(finalBarMsg)));

        //join message
        if (!player.hasPlayedBefore() && UseFirstJoinMsg){
            List <String> list;
            list = BetterJoins.getPlugin(BetterJoins.class).getConfig().getStringList("FirstJoin");
            for (String s : list) {
                FirstJoin(s.replace("%player%", player.getName()));
            }

            int UniqueJoins = BetterJoins.getPlugin(BetterJoins.class).getConfig().getInt("UniqueJoins");
            UniqueJoins++;
            BetterJoins.getPlugin(BetterJoins.class).getConfig().set("UniqueJoins", UniqueJoins);


            event.setJoinMessage(null);
        } else {
            List <String> list;
            list = BetterJoins.getPlugin(BetterJoins.class).getConfig().getStringList("JoinMsg");
            Random random = new Random();
            int rand = random.nextInt(list.size());
            String finalWbMsg = list.get(rand).replace("%player%", player.getName());
            event.setJoinMessage(ColorUtils.translateColorCodes(finalWbMsg));
        }









    }


    private void FirstJoin(String... lines) {
        int UniqueJoins = BetterJoins.getPlugin(BetterJoins.class).getConfig().getInt("UniqueJoins");
        String finalLine = Arrays.toString(lines).replace("%joins%", String.valueOf(UniqueJoins)).replace("[","").replace("]","");
        Bukkit.broadcastMessage(ColorUtils.translateColorCodes(finalLine));
    }
}
