package me.VoltMC.VoltSMP;

import me.VoltMC.VoltSMP.Commands.AdminCommands;
import me.VoltMC.VoltSMP.Commands.Playtime;
import me.VoltMC.VoltSMP.Commands.RankGUI;
import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Functions.playtimeEvents;
import me.VoltMC.VoltSMP.Ranks.GoalEvents.Miner;
import me.VoltMC.VoltSMP.Ranks.GoalEvents.Traveler;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private static Main instance;
    private LuckPerms luckPerms;

    @Override
    public void onEnable() {
        String version = this.getConfig().getString("version");
        instance = this;

        // LuckPerms Registry
        luckPerms = LuckPermsProvider.get();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events.
        getServer().getPluginManager().registerEvents(new playtimeEvents(), this);
        getServer().getPluginManager().registerEvents(new Traveler(), this );
        getServer().getPluginManager().registerEvents(new Miner(), this);

        // File Loads.
        try {
            PlaytimeData.Load();
            TravelerTrack.Load();
            MinerTrack.Load();
            this.getLogger().log(Level.INFO, "File Data has loaded successfully.");
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        getCommand("testgui").setExecutor(new RankGUI());
        getCommand("playtime").setExecutor(new Playtime());
        getCommand("voltadmin").setExecutor(new AdminCommands());


        // Player Data File Load.
        PlaytimeData.loadPlayerTimes();
        this.getLogger().log(Level.INFO, "Player Data has loaded successfully.");

        this.getLogger().log(Level.INFO, "VoltSMP Main Plugin has been started. " + version);
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, "If there is anything wrong with the loading param please contact Ghostinq on Discord.");

    }

    @Override
    public void onDisable() {
        PlaytimeData.Save();
        TravelerTrack.Save();
        MinerTrack.Save();
    }


    public LuckPerms luckAPI() {
        return luckPerms;
    }

    public String getPrefix() {
        return getInstance().getConfig().getString("prefix");
    }

    public static Main getInstance() {
        return instance;
    }
}
