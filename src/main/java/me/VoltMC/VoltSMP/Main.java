package me.VoltMC.VoltSMP;

import me.VoltMC.VoltSMP.Commands.AdminCommands;
import me.VoltMC.VoltSMP.Commands.PlaytimeCommand;
import me.VoltMC.VoltSMP.Commands.RankGUI;
import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.FileManip.RankRewards.TravelRewards;
import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Ranks.GUIEvents.RankAquireClick;
import me.VoltMC.VoltSMP.Ranks.GoalEvents.Miner;
import me.VoltMC.VoltSMP.Ranks.GoalEvents.Playtime;
import me.VoltMC.VoltSMP.Ranks.GoalEvents.Traveler;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private static Main instance;
    private LuckPerms luckPerms;
    private static Economy econ;

    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        String version = this.getConfig().getString("version");
        instance = this;

        // LuckPerms Registry
        luckPerms = LuckPermsProvider.get();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events.
        getServer().getPluginManager().registerEvents(new Traveler(), this );
        getServer().getPluginManager().registerEvents(new Miner(), this);
        getServer().getPluginManager().registerEvents(new RankAquireClick(), this);
        getServer().getPluginManager().registerEvents(new Playtime(), this);

        // File Loads.
        try {
            PlaytimeData.Load();
            TravelerTrack.Load();
            MinerTrack.Load();
            TravelRewards.Load();
            this.getLogger().log(Level.INFO, "File Data has loaded successfully.");
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        getCommand("testgui").setExecutor(new RankGUI());
        getCommand("playtime").setExecutor(new PlaytimeCommand());
        getCommand("voltadmin").setExecutor(new AdminCommands());


        // Player Data File Load.
        PlaytimeData.loadPlayerTimes();
        this.getLogger().log(Level.INFO, "Player Data has loaded successfully.");

        this.getLogger().log(Level.INFO, "VoltSMP Main Plugin has been started. " + version);
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, "If there is anything wrong with the loading param please contact Ghostinq on Discord.");

        // Runnables
        Playtime.TrackPlaytime();
    }

    @Override
    public void onDisable() {
        PlaytimeData.Save();
        TravelerTrack.Save();
        MinerTrack.Save();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
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
