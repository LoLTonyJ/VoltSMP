package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Traveler {

    public static ItemStack travelItem(Player p) {

        // Param @p will be used once config files are running smoothly. Until then, it is a placeholder for future inputs.

        String blocks = Main.getInstance().getConfig().getString("Miner.Tier_" + MinerTrack.getTier(p) + ".Blocks");

        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bTraveler Rewards"));
        List<String> lore = new ArrayList<>();
        if (blocks == null) {
            lore.add(" ");
            lore.add(ChatColor.format("&aYou have completed all the goals! Congratulations!"));
        }
        lore.add(" ");
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- &aWalking " + blocks + " blocks"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
