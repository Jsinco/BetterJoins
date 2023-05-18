package me.jsinco.betterjoins;

import me.jsinco.betterjoins.commands.Commands;
import me.jsinco.betterjoins.commands.TabCompleter;
import me.jsinco.betterjoins.commands.ToggleJoinSounds;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterJoins extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);

        getCommand("betterjoins").setExecutor(new Commands());
        getCommand("betterjoins").setTabCompleter(new TabCompleter());
        getCommand("joinsounds").setExecutor(new ToggleJoinSounds());
    }
}
