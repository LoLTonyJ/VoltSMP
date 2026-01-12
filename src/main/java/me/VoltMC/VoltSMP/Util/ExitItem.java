package me.VoltMC.VoltSMP.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExitItem {

    public static ItemStack exit() {
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&c&lExit"));
        item.setItemMeta(meta);

        return item;
    }

}
