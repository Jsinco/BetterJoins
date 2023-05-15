package me.jsinco.betterjoins;

import me.jsinco.betterjoins.commands.ParticleEdit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterJoins extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
