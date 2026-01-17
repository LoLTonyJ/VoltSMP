package me.VoltMC.VoltSMP.Util;

public class ChatColor {

    public static String format(String s) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String strip(String s) {
        return org.bukkit.ChatColor.stripColor(s);
    }

}
