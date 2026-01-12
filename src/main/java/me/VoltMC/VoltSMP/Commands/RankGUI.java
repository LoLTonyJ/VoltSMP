package me.VoltMC.VoltSMP.Commands;

import me.VoltMC.VoltSMP.Ranks.GUI.ObtainRankGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RankGUI implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        Player p = (Player) sender;

        p.openInventory(ObtainRankGUI.RankGUI(p));


        return true;
    }
}
