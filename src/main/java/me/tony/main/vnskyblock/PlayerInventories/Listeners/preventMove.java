package me.tony.main.vnskyblock.PlayerInventories.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class preventMove implements Listener {

    @EventHandler
    public void noMove(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();

        if (inv.getType().equals(InventoryType.CRAFTING)) {
            if (e.getCurrentItem().getType().equals(Material.NETHER_STAR) && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                e.setCancelled(true);
            }
        }
    }


}
