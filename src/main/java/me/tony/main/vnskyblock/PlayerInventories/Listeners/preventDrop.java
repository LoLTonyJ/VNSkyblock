package me.tony.main.vnskyblock.PlayerInventories.Listeners;

import me.tony.main.vnskyblock.PlayerInventories.Items.playerInformation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class preventDrop implements Listener {


    @EventHandler
    public void noDrop(PlayerDropItemEvent e) {

        ItemStack i = e.getItemDrop().getItemStack();

        if (i.equals(playerInformation.generalInformation())) {
            e.setCancelled(true);
        }

    }


}
