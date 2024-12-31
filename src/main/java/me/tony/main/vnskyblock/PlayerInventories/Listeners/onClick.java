package me.tony.main.vnskyblock.PlayerInventories.Listeners;

import me.tony.main.vnskyblock.PlayerInventories.Inventories.generalInformationInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onClick implements Listener {

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack i = e.getItem();

        if (i == null || !i.getType().equals(Material.NETHER_STAR) || !i.getItemMeta().hasDisplayName()) return;
        p.openInventory(generalInformationInventory.informationInv(p));


    }



}
