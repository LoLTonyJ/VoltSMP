package me.VoltMC.VoltSMP.Ranks.RankItems;

import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Builder {

    public static ItemStack builderItem(Player p) {
        ItemStack item = new ItemStack(Material.BRICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bBuilder Rewards"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("obtainable by blah blah"); // Come back to this
        lore.add(" ");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
