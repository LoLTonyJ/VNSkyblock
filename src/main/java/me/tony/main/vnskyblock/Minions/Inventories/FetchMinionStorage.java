package me.tony.main.vnskyblock.Minions.Inventories;

import me.tony.main.vnskyblock.Minions.Methods.MinionManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class FetchMinionStorage {



    public static Inventory openMinionStorage(Player p, ArmorStand minion) {

        Inventory inv = Bukkit.createInventory(p, 9*5, ChatColor.format("&b" + p.getName() + "'s Minion Storage"));

        int[] num = {12, 13, 14, 21, 22, 23, 30, 31, 32};
        List<ItemStack> minionStorage = MinionManager.getMinionStorage(minion.getUniqueId());
        int count = 0;
        Set<Integer> airSlots = new HashSet<>();

        debug.print(String.valueOf(minionStorage.size()));

        for (int n : num) {
            airSlots.add(n);
        }
        for (int s = 0; s <= 44; s++) {
            if (airSlots.contains(s)) {
                if (count < minionStorage.size()) {
                    ItemStack item = minionStorage.get(count);
                    inv.setItem(s, item);
                }
                count++;
            } else {
                inv.setItem(s, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }
        p.openInventory(inv);
        return inv;
    }


}
