package me.VoltMC.VoltSMP.Ranks.GUIEvents;

import me.VoltMC.VoltSMP.FileManip.RankRewards.TravelRewards;
import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class RankAquireClick implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        int currentTier = TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");

        int targetBlockTravel = Integer.parseInt(Main.getInstance().getConfig().getString("Traveler.Blocks"));
        int targetBlockMiner = Integer.parseInt(Main.getInstance().getConfig().getString("Miner.Blocks"));

        int playerRankTierTravel = TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");
        int playerRankTierMiner = TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");


        Inventory inv = e.getInventory();
        InventoryView view = e.getView();
        if (view.getTitle().equals(ChatColor.format("&bObtainable Ranks"))) {
            e.setCancelled(true);
            int slotClicked = e.getSlot();
            if (slotClicked == 11) {
               // if (TravelerTrack.getPlayerBlock(p) >= targetBlockTravel) {
                    if (TravelRewards.getList(TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier")).isEmpty()) return; // Returns if the tier list is empty. Avoids Console Spam + Errors.
                    for (String rewardType : TravelRewards.getList(TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier"))) { // Loops through, and iterates through list of rewards.
                        if (!rewardType.equalsIgnoreCase("money")) { // Money.
                            String tierSection = "traveler_tier_" + TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier"); // Gets players Tier.
                            Material material = Material.matchMaterial(rewardType); // Translates String -> Material, if applicable.
                            int amount = TravelRewards.getRewardAmount(tierSection + "." + rewardType); // Gets the amount of reward(s)
                            if (material != null) { // Checks to see if "Material" is in Minecraft.
                                ItemStack item = new ItemStack(material, amount);
                                p.getInventory().addItem(item);
                            }
                        } else {
                            // Money rewards.
                            String tierSection = "traveler_tier_" + TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");
                            int amount = TravelRewards.getRewardAmount(tierSection + "." + rewardType);
                            TravelRewards.giveMoney(p, amount);
                        }
                    }
                    TravelerTrack.addTier(p, currentTier + 1);
              //  }
            }
            e.setCancelled(true);

        }
    }


}
