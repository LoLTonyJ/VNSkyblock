package me.tony.main.vnskyblock.CustomItems.Inventories;

import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class onClickTPStick implements Listener {

    @EventHandler
    public static void onClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format("&dUpgrade Teleport Stick?"))) {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 15) {
                for (ItemStack item : p.getInventory()) {
                    if (item.getType().equals(Material.ENDER_PEARL) && PDCUtil.itemKeyValue(item, Keys.ITEM_ID, "Transmute_Enderpearl")) {
                        p.closeInventory();
                        return;
                    }
                }
            }
        }


    }


}
