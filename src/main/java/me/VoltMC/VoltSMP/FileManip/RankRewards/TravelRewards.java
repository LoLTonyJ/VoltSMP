package me.VoltMC.VoltSMP.FileManip.RankRewards;

import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class TravelRewards {

    private static File file;
    private static YamlConfiguration config;

    public static void giveMoney(Player p, Integer amount) {
        Main.getEconomy().bankDeposit(p.getName(), amount);
        p.sendMessage(ChatColor.format("&7You have been given &a$" + amount));
    }

    public static Integer getRewardAmount(String path) {
        if (path == null) return 0;
        return config.getInt(path);
    }

    public static List<String> getList(Integer Tier) {
        ConfigurationSection rewardSection = config.getConfigurationSection("traveler_tier_" + Tier);
            if (rewardSection == null) {
                Main.getInstance().getLogger().log(Level.SEVERE, "Reward List Came back as NULL! (Travel)");
                return new ArrayList<>();
            }
            return new ArrayList<>(rewardSection.getKeys(false));
        }

    public static void Save() {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/RankRewards/TravelerRewards.yml");

        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(Main.getInstance().getDataFolder(), "RankProgress/RankRewards/TravelerRewards.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) Main.getInstance().saveResource("RankProgress/RankRewards/TravelerRewards.yml", false);
        config.load(file);
    }


}
