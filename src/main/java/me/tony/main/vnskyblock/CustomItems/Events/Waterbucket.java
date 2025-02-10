package me.tony.main.vnskyblock.CustomItems.Events;

import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Waterbucket implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Action a = e.getAction();
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

        if (!item.getType().equals(Material.WATER_BUCKET)) return;

        if (PDCUtil.itemContainsKey(Keys.ITEM_ID, item) && PDCUtil.itemKeyValue(item, Keys.ITEM_ID, "Infinite_Water")) {
            if (a.equals(Action.RIGHT_CLICK_BLOCK)) {
                Block b = e.getClickedBlock();
                Location bLoc = b.getLocation().add(0, 1, 0);
                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                    Block setB = bLoc.getBlock();
                    setB.setType(Material.WATER);
                    e.setCancelled(true);
                } else if (b.getType().equals(Material.WATER) || bLoc.getBlock().getType().equals(Material.WATER)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
