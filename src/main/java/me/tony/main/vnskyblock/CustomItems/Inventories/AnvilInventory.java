package me.tony.main.vnskyblock.CustomItems.Inventories;

import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class AnvilInventory {

    public static Inventory anvilInventory(Player p) {
        int[] num = {11, 13, 16};
        Inventory inv = Bukkit.createInventory(null, 9 *3, ChatColor.format("&7Upgrade Anvil"));
        Set<Integer> airSlots = new HashSet<>();
        for (int n : num) {
            airSlots.add(n);
        }
        for (int s = 0; s <= 26; s++) {
            if (!airSlots.contains(s)) {
                inv.setItem(s, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }

        }

        return inv;
    }


}
