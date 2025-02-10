package me.tony.main.vnskyblock.PlayerInventories.Inventories;

import me.tony.main.vnskyblock.PlayerInventories.Items.backpackStorage;
import me.tony.main.vnskyblock.PlayerInventories.Items.petStorage;
import me.tony.main.vnskyblock.PlayerInventories.Items.playerSkull;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class generalInformationInventory {

    public static Inventory informationInv(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9*5, ChatColor.format("&bGeneral Information"));

        inv.setItem(13, playerSkull.skull(p));
        inv.setItem(23, petStorage.petItem());
        inv.setItem(24, backpackStorage.backItem());


        p.openInventory(inv);
        return inv;
    }



}
