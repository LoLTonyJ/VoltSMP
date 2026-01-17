package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Traveler {

    public static ItemStack travelItem(Player p) {

        // Param @p will be used once config files are running smoothly. Until then, it is a placeholder for future inputs.

        String blocks = Main.getInstance().getConfig().getString("Traveler.Blocks");
        Integer blockGoal = Integer.parseInt(Main.getInstance().getConfig().getString("Traveler.Blocks"));

        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bTraveler Rank"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- &aWalking " + blocks + " blocks"));
        if (TravelerTrack.getPlayerBlock(p) <= blockGoal) {
            lore.add(" ");
            lore.add(ChatColor.format("&aYou have traveled " + TravelerTrack.getPlayerBlock(p) + " Blocks"));
            lore.add(" ");
        } else {
            lore.add(" ");
            lore.add(ChatColor.format("&a&lClick to claim your Reward!"));
            lore.add(" ");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
