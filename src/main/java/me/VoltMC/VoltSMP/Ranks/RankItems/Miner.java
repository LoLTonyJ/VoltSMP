package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Miner {

    public static ItemStack minerItem(Player p) {

       String blockGoal = Main.getInstance().getConfig().getString("Miner.Blocks");

       // test commit init

        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bMiner Rank"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- Mining " + blockGoal + " Blocks"));

        return item;
    }

}
