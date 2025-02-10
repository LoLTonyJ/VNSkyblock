package me.tony.main.vnskyblock.NPC.Inventories;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.inventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ElizabethInventory {

    public static Inventory premiumInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.format("&dElizabeth Shop"));



        inventoryUtil.FillInventory(Material.BLACK_STAINED_GLASS, inv);
        p.openInventory(inv);
        return inv;
    }

}
