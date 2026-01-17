package me.VoltMC.VoltSMP.Ranks.GUI;

import me.VoltMC.VoltSMP.Ranks.RankItems.Miner;
import me.VoltMC.VoltSMP.Ranks.RankItems.Traveler;
import me.VoltMC.VoltSMP.Util.Filler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class ObtainRankGUI {

    // Testing Placements, also style.
    // For loops will be turned down once things are set in stone, until then it is easier.

    public static Inventory RankGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.AQUA + "Obtainable Ranks"); // bing bong.

        // Filler Item
        int[] filler = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47 ,48, 49, 50, 51, 52, 53}; // Blah blah numbers, numbers.
        for (int i = 0; i <= inv.getSize(); i++){ // Loops through Empty Inventory Slots.
            for (int f : filler) { // Checks if f == int from filler[] (Line 21)
                if (i == f) inv.setItem(f, Filler.redFiller()); // If f == filler[] inputs filler item.
            }
        }

        inv.setItem(11, Traveler.travelItem(p));
        inv.setItem(12, Miner.minerItem(p));

        p.openInventory(inv);
        return inv;
    }




}
