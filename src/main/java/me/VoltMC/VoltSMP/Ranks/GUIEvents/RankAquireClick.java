package me.VoltMC.VoltSMP.Ranks.GUIEvents;

import me.VoltMC.VoltSMP.FileManip.RankRewards.MinerRewards;
import me.VoltMC.VoltSMP.FileManip.RankRewards.TravelRewards;
import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
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
        int currentTravelTier = TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");
        int currentMinerTier = MinerTrack.getInt("players." + p.getUniqueId() + ".Tier");

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
                if (TravelRewards.getList(currentTravelTier).isEmpty())
                    return; // Returns if the tier list is empty. Avoids Console Spam + Errors.
                for (String rewardType : TravelRewards.getList(currentTravelTier)) { // Loops through, and iterates through list of rewards.
                    if (!rewardType.equalsIgnoreCase("money")) { // Money.
                        String tierSection = "traveler_tier_" + currentTravelTier; // Gets players Tier.
                        Material material = Material.matchMaterial(rewardType); // Translates String -> Material, if applicable.
                        int amount = TravelRewards.getRewardAmount(tierSection + "." + rewardType); // Gets the amount of reward(s)
                        if (material != null) { // Checks to see if "Material" is in Minecraft.
                            ItemStack item = new ItemStack(material, amount);
                            p.getInventory().addItem(item);
                        } else {
                            Main.getInstance().getLogger().log(Level.SEVERE, rewardType + " isn't a valid block/item in Minecraft, please check spelling in your Travel rewards configuration!");
                            return;
                        }
                    } else {
                        // Money rewards.
                        String tierSection = "traveler_tier_" + TravelerTrack.getInt("players." + p.getUniqueId() + ".Tier");
                        int amount = TravelRewards.getRewardAmount(tierSection + "." + rewardType);
                        TravelRewards.giveMoney(p, amount);
                    }
                }
                TravelerTrack.addTier(p, currentTravelTier + 1);
                //  }
            }
            if (slotClicked == 12) {
                String minerTier = "miner_tier_" + currentMinerTier;
                if (MinerTrack.getPlayerBlock(p) >= targetBlockMiner) {
                    if (MinerRewards.getList(currentMinerTier).isEmpty()) return;
                    for (String rewardType : MinerRewards.getList(currentMinerTier)) {
                        if (!rewardType.equalsIgnoreCase("money")) {
                            Material material = Material.matchMaterial(rewardType);
                            int amount = MinerRewards.getRewardAmount(minerTier + "." + rewardType);
                            if (material != null) {
                                ItemStack item = new ItemStack(material, amount);
                                p.getInventory().addItem(item);
                            } else {
                                Main.getInstance().getLogger().log(Level.SEVERE, rewardType + " isn't a valid block/item in Minecraft, please check spelling in your Miner rewards configuration!");
                                return;
                            }
                        } else {
                            int amount = MinerRewards.getRewardAmount(minerTier + "." + rewardType);
                            MinerRewards.giveMoney(p, amount);
                        }
                    }
                    MinerTrack.addTier(p, currentTravelTier + 1);
                }
            }
        }
    }
}
