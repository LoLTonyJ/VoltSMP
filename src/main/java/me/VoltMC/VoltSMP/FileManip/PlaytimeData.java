package me.VoltMC.VoltSMP.FileManip;

import me.VoltMC.VoltSMP.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlaytimeData {

    private static File file;
    private static YamlConfiguration config;



    public static Integer getPlayerTime(Player p) {
        return config.getInt("players." + p.getUniqueId() + ".Time");
    }

    public static Integer getPlayerTier(Player p) {
        return config.getInt("players." + p.getUniqueId() + ".Tier");
    }

    public static void initPlayerTime(Player p, Integer time) { // New Players.
        ConfigurationSection players = config.getConfigurationSection("players");
        if (config.get(players + "." + p.getUniqueId()) == null) {
            config.set(players + "." + p.getUniqueId() + ".Time", time); // Inits Player(s) Time.
            config.set(players + "." + p.getUniqueId() + ".Tier", 0);
        }
    }

    public static void savePlayerTime(Player p, Integer time) {
        ConfigurationSection players = config.getConfigurationSection("players");
        players.set(p.getUniqueId() + ".Time", time);
        Save();
    }

    // this may or may not work, who knows....
    public static void loadPlayerTimes() {
        if (config.getConfigurationSection("players") != null) {
            for (String pUUID : config.getConfigurationSection("players").getKeys(false)) {
                int playerTime = config.getInt("players." + pUUID + ".Time");
               // playtimeEvents.playtimeTemp.put(UUID.fromString(pUUID), playerTime);
            }
        }
    }

    public static void Save() {
        file = new File(Main.getInstance().getDataFolder(), "PlayerData/Playtime.yml");

        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(Main.getInstance().getDataFolder(), "PlayerData/Playtime.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) Main.getInstance().saveResource("PlayerData/Playtime.yml", false);
        config.load(file);
    }

}
