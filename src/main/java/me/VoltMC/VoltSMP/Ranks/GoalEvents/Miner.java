package me.VoltMC.VoltSMP.Ranks.GoalEvents;

import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class Miner implements Listener {

    private static HashMap<UUID, Integer> tempTrack = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        MinerTrack.initPlayer(p);
        int num = MinerTrack.getPlayerBlock(p);
        if (!tempTrack.containsKey(p.getUniqueId())) {
            tempTrack.put(p.getUniqueId(), num);
        }
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        String blockType = b.getType().toString();
        if (p.getGameMode().equals(GameMode.CREATIVE)) return; // Creative Players will not Track.

        // Will make a String list later on, for cleaner code.
        if (blockType.toLowerCase().contains("seeds")) return; // Cancels out any Farming Blocks.
        if (blockType.toLowerCase().contains("cane")) return; // Cancels out Sugar Cane
        if (blockType.toLowerCase().contains("bamboo")) return; // Cancels out Bamboo "Block"
        if (blockType.toLowerCase().contains("kelp")) return; // Cancels out Kelp "Block"
        if (blockType.toLowerCase().contains("cake")) return; // Cancels out Cake "Block"
        if (blockType.toLowerCase().contains("cluster")) return; // Cancels out Amethyst Clusters.
        if (!tempTrack.containsKey(p.getUniqueId())) return;
        int numBlock = tempTrack.get(p.getUniqueId());
        tempTrack.remove(p.getUniqueId());
        tempTrack.put(p.getUniqueId(), numBlock + 1);
        MinerTrack.editPlayerBlocks(p, numBlock + 1);
    }

}
