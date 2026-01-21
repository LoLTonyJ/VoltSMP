package me.VoltMC.VoltSMP.Ranks.GoalEvents;

import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Playtime implements Listener {

    private static HashMap<Player, Integer> playtimeTracker = new HashMap<>();
    private static HashMap<Player, Location> afkTracker = new HashMap<>(); // Will add an AFK checker Method later on.

    public static Integer playerPlaytime(Player p) {
        return playtimeTracker.get(p);
    }

    public static boolean isAFK(Player p) {
        Location pLoc = p.getLocation();
        return (afkTracker.get(p) == pLoc);
    }

    public static void editPlayerTime(Player p, Integer time) {
        if (!playtimeTracker.containsKey(p)) return;
        playtimeTracker.remove(p);
        playtimeTracker.put(p, time + 1);
    }

    public static void TrackPlaytime() {
        new BukkitRunnable() {
            @Override
            public void run() {
              //  if (playtimeTracker.isEmpty()) this.cancel();
                for (Player p : playtimeTracker.keySet()) {
                    if (p == null) return;
                    if (playtimeTracker.get(p) == null) return;
                    afkTracker.put(p, p.getLocation());
                    if (isAFK(p)) {
                        System.out.println(p.getName() + " is AFK, stopping playtime tracker for this player.");
                        return;
                    }
                    int currentTime = playtimeTracker.get(p);
                    editPlayerTime(p, currentTime);
                }
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0L, 20L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        PlaytimeData.savePlayerTime(e.getPlayer(), playerPlaytime(e.getPlayer()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlaytimeData.initPlayerTime(p, 0);
        if (playtimeTracker.containsKey(p)) return;
        playtimeTracker.put(p, PlaytimeData.getPlayerTime(p));
    }




}
