package me.VoltMC.VoltSMP.FileManip.RankTracking;

import me.VoltMC.VoltSMP.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class TravelerTrack {

    private static File file;
    private static YamlConfiguration config;

    public static void editPlayerBlocks(Player p, Integer blockNum) {
        if (config.get("players." + p.getUniqueId()) != null) {
            config.set("players." + p.getUniqueId() + ".Blocks", blockNum);
            Save();
        }
    }

    public static void initPlayer(Player p) {

        ConfigurationSection sect = config.getConfigurationSection("players");
        if (sect == null) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Block Tracker is returning NULL! Contact Ghostinq on Discord!");
            return;
        }

        if (sect.get(p.getUniqueId().toString()) == null) {
            sect.set(p.getUniqueId() + ".Blocks", 0);
        }
    }

    public static Integer getPlayerBlock(Player p) {
        return config.getInt("players." + p.getUniqueId() + ".Blocks");
    }

    public static void Save() {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/TravelerProgress.yml");

        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/TravelerProgress.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) Main.getInstance().saveResource("RankProgress/TravelerProgress.yml", false);
        config.load(file);
    }
}
