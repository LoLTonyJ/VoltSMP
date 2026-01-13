package me.VoltMC.VoltSMP.Commands;

import me.VoltMC.VoltSMP.FileManip.PlaytimeData;
import me.VoltMC.VoltSMP.Main;
import me.VoltMC.VoltSMP.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Playtime implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        Player p = (Player) sender;
        String prefix = Main.getInstance().getPrefix();

        p.sendMessage(ChatColor.format(prefix + "&aYour playtime: " + PlaytimeData.getPlayerTime(p)));

        if (p.hasPermission("VoltSMP.Admin")) {
             if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.format("&cThat player does not exist!"));
                } else {
                    p.sendMessage(ChatColor.format(prefix + " &7" + target.getName() + "'s Playtime; " + PlaytimeData.getPlayerTime(target)));
                }
            }
        }

        return true;
    }
}
