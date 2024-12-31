package me.tony.main.vnskyblock.Minions.Inventories;

import me.tony.main.vnskyblock.Minions.Inventories.MinionFunctions.MineBlock;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class cobbleMinionStorage {

    public static Inventory minionStorage(Player p, List<ItemStack> minion) {

        Inventory inv = Bukkit.createInventory(p, 9*5, chatUtil.format("&bMinion Storage"));
        // 12, 13, 14, 21, 22, 23, 30, 31, 32
        for (int i = 0; i <= 44; i++) {
            if (i == 12 || i == 13 || i == 14 || i == 21 || i == 22 || i == 23 || i == 30 || i == 31 || i == 32) { // not ideal but it works for now.
                if (minion.isEmpty()) {
                    inv.setItem(i, new ItemStack(Material.AIR));
                }
                for (ItemStack item : minion) {
                    int index = 0;
                    if (minion.get(index) != null) {
                        inv.setItem(i, minion.get(index));
                    }
                }
            } else {
                inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }
        p.openInventory(inv);
        return inv;
    }


}
