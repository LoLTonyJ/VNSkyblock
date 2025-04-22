package me.tony.main.vnskyblock.Util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryNoClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().contains(ChatColor.format("&7Loadout"))) {
            if (!permCheck.isAdmin((Player) e.getWhoClicked())) {
                e.setCancelled(true);
            }
        }
    }
}
