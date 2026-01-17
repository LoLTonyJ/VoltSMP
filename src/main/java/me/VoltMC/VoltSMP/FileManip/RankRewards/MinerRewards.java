package me.VoltMC.VoltSMP.FileManip.RankRewards;

import me.VoltMC.VoltSMP.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinerRewards {

    private static File file;
    private static YamlConfiguration config;

    public static void Save() {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/RankRewards/MinerRewards.yml");

        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/RankRewards/MinerRewards.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) Main.getInstance().saveResource("RankProgress/RankRewards/MinerRewards.yml", false);
        config.load(file);
    }


}
