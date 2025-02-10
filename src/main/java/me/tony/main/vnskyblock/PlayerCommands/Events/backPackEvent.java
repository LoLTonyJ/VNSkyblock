package me.tony.main.vnskyblock.PlayerCommands.Events;

import me.tony.main.vnskyblock.PlayerCommands.Methods.backPackMethods;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class backPackEvent implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();

        if (e.getView().getTitle().equals(ChatColor.format("&b" + p.getDisplayName() + "'s backpack"))) {
            backPackMethods.updateBackpack(p, inv);
        }

    }


}
