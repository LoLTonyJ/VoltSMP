package me.VoltMC.VoltSMP.Commands;

import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.FileManip.RankTracking.MinerTrack;
import me.VoltMC.VoltSMP.FileManip.RankTracking.TravelerTrack;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        Player p = (Player) sender;

        if (p.hasPermission("VoltSMP.Admin")) {
            if (args.length == 2) {
                String arg = args[0];
                if (arg.equalsIgnoreCase("tracker")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    p.sendMessage(ChatColor.format(Main.getInstance().getPrefix() + " &7" + target.getName() + " Tracker Stats"));
                    p.sendMessage(ChatColor.format("&aTraveled Blocks: " + TravelerTrack.getPlayerBlock(target)));
                    p.sendMessage(ChatColor.format("&aBroken Blocks: " + MinerTrack.getPlayerBlock(target)));
                    p.sendMessage(ChatColor.format("&aPlaytime: " + PlaytimeData.getPlayerTime(target)));
                }
            }
        }

        if (p.hasPermission("VoltSMP.Dev")) {
            if (args.length == 1) {
                String arg = args[0];
                if (arg.equalsIgnoreCase("reload") || arg.equalsIgnoreCase("rl")) {
                    Main.getInstance().reloadConfig();
                    p.sendMessage(ChatColor.format(Main.getInstance().getPrefix() + " &aConfig has been reloaded!"));
                }
            }
        }

        return true;
    }
}
