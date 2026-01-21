package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gamer {

    public static ItemStack gamerItem(Player p) {
        ItemStack item = new ItemStack(Material.REDSTONE_WIRE);
        ItemMeta meta = item.getItemMeta();

        String gamerGoal = Main.getInstance().getConfig().getString("gamer." + ".tier_" + PlaytimeData.getPlayerTime(p));

        meta.setDisplayName(ChatColor.format("&b&lGamer Rewards"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- Playing for " + gamerGoal + " Seconds"));
        lore.add(" ");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }


}
