package me.VoltMC.VoltSMP.Ranks.GoalEvents;

import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class Traveler implements Listener {

    private static HashMap<UUID, Integer> blockTracker = new HashMap<>();
    private static ArrayList<UUID> noSpam = new ArrayList<>();

    public static Integer getBlocks(Player p) {
        UUID pUUID = p.getUniqueId();
        return blockTracker.get(pUUID);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        LivingEntity le = p;
        int blockGoal = Integer.parseInt(Main.getInstance().getConfig().getString("Traveler.Blocks"));

        if (!p.isOnGround()) return; // Checks if the player is Flying
        if (p.isGliding()) return; // Checks if the player is using an Elytra
        if (p.isInWater()) return; // Cancels out AFK Water Machines.
        if (p.isInsideVehicle()) return; // Checks if the player is in a Boat / Minecart
        if (!e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            if (blockTracker.containsKey(p.getUniqueId())) {
                if (getBlocks(p) >= blockGoal) {
                    if (!noSpam.contains(p.getUniqueId())) return;
                    p.sendMessage(ChatColor.format("&b&lYou have reached your Travel goal! You can claim your rewards!"));
                    noSpam.add(p.getUniqueId());
                }
                int blockNum = blockTracker.get(p.getUniqueId());
                blockTracker.remove(p.getUniqueId());
                blockTracker.put(p.getUniqueId(), blockNum + 1);
                TravelerTrack.editPlayerBlocks(p, getBlocks(p));
                TravelerTrack.Save();
                return;
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        TravelerTrack.initPlayer(e.getPlayer());
        int num = TravelerTrack.getPlayerBlock(e.getPlayer());
        if (!blockTracker.containsKey(e.getPlayer().getUniqueId())) {
            blockTracker.put(e.getPlayer().getUniqueId(), num);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!noSpam.contains(p.getUniqueId())) return;
        noSpam.remove(p.getUniqueId());
    }

}
