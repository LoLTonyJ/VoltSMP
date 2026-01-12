package me.VoltMC.VoltSMP.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Filler {

    public static ItemStack redFiller() {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&c "));
        meta.getItemFlags().clear();
        item.setItemMeta(meta);
        return item;
    }

}
