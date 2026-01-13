package me.VoltMC.VoltSMP.Functions;

import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class playtimeEvents implements Listener {

    public static HashMap<UUID, Integer> playtimeTemp = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (playtimeTemp.get(e.getPlayer().getUniqueId()) == null) {
            PlaytimeData.initPlayerTime(e.getPlayer(), 0);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (playtimeTemp.containsKey(e.getPlayer().getUniqueId())) {
            PlaytimeData.savePlayerTime(e.getPlayer(), playtimeTemp.get(e.getPlayer()));
        }
    }

}
