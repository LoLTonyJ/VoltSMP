package me.VoltMC.VoltSMP.Ranks.RankItems;

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

        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bTraveler Rank"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- &aWalking " + blocks + " blocks"));
        lore.add(" ");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
