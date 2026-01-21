package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.FileManip.RankRewards.MinerRewards;
import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Miner {

    public static ItemStack minerItem(Player p) {

     // Miner . Tier_1/2/3 . Blocks.
       String blockGoal = Main.getInstance().getConfig().getString("Miner.Tier_" + MinerTrack.getTier(p) + ".Blocks");

        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bMiner Rewards"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&bObtainable by;"));
        lore.add(ChatColor.format("&7- Mining " + blockGoal + " Blocks"));
        lore.add(" ");
        lore.add(ChatColor.format("&aYou have mined " + MinerTrack.getPlayerBlock(p) + " Blocks!"));
        lore.add(" ");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
