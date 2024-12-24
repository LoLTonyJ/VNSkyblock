package me.tony.main.vnskyblock.Util;

import org.bukkit.ChatColor;

import java.util.List;

public class loreCheck {

    public static boolean containsWord(List<String> lore, String contains) {
       if (lore == null || lore.isEmpty()) {
           debug.print("no lore");
           return false;
       }
       for (String line : lore) {
           String strippedLine = ChatColor.stripColor(line);
           if (strippedLine.contains(contains)) return true;
       }
       return false;
    }


}
