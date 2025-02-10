package me.tony.main.vnskyblock.Util;

public class ChatColor {

    public static String format(String msg) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
    }

}
