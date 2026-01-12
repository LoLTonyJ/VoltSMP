package me.VoltMC.VoltSMP;

import me.VoltMC.VoltSMP.Commands.RankGUI;
import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    static Main instance;

    @Override
    public void onEnable() {
        String version = this.getConfig().getString("version");
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // File Loads.
        try {
            PlaytimeData.Load();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        getCommand("testgui").setExecutor(new RankGUI());

        this.getLogger().log(Level.INFO, "VoltSMP Main Plugin has been started. " + version);
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, "If there is anything wrong with the loading param please contact Ghostinq on Discord.");

    }

    @Override
    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }
}
