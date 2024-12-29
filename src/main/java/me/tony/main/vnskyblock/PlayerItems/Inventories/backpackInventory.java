package me.tony.main.vnskyblock.PlayerItems.Inventories;

import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class backpackInventory {

    public static Inventory storage(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*4, chatUtil.format("&bBackpack Storage"));



        return inv;
    }


}
