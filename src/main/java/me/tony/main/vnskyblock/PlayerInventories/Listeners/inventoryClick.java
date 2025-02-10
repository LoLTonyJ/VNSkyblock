package me.tony.main.vnskyblock.PlayerInventories.Listeners;

import me.tony.main.vnskyblock.PetUtil.Inventories.petDisplay;
import me.tony.main.vnskyblock.PlayerCommands.Methods.backPackMethods;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals(ChatColor.format("&bGeneral Information"))) {
            e.setCancelled(true);
            if (e.getSlot() == 24) {
                backPackMethods.playerBackpack(p);
            }
            if (e.getSlot() == 23) {
                petDisplay.petInventory(p);
            }
        }

    }
}
