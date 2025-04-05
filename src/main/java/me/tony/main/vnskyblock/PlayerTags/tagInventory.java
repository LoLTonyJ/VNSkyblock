package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.inventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class tagInventory {

    public static Inventory displayTags(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, ChatColor.format("&7Player Tags"));
        for (String s : tagUtil.displayPlayerTags(p)) {
            inv.addItem(inventoryUtil.createGUIItem(Material.PAPER, ChatColor.format(s), ChatColor.format(tagData.getTagContent(s)), " ", " ", " ", " "));
        }
        p.openInventory(inv);
        return inv;
    }

}
