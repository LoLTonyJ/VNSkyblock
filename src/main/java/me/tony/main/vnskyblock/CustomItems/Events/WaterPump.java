package me.tony.main.vnskyblock.CustomItems.Events;

import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.direction;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class WaterPump implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Vector dir = p.getLocation().getDirection();
        dir.normalize();

        if (!item.getType().equals(Material.DARK_PRISMARINE)) return;

        Block placedBlock = e.getBlockPlaced();
        if (PDCUtil.itemHasKey(Keys.ITEM_ID, item) && PDCUtil.itemStringKey(item, Keys.ITEM_ID, "water_pump")) {
            placedBlock.setType(Material.WATER);
            item.setAmount(item.getAmount() - 1);
            for (int i = 1; i <= 30; i++) {
                if (direction.getCardinalDirection(p).equalsIgnoreCase("SOUTH")) {
                    Block b = placedBlock.getLocation().add(0, 0, i).getBlock();
                    if (b.getType() == Material.AIR) {
                        b.setType(Material.WATER);
                    } else {
                        break;
                    }
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("NORTH")) {
                    Block b = placedBlock.getLocation().add(0, 0, -i).getBlock();
                    if (b.getType() == Material.AIR) {
                        b.setType(Material.WATER);
                    } else {
                        break;
                    }
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("WEST")) {
                    Block b = placedBlock.getLocation().add(-i, 0, 0).getBlock();
                    if (b.getType() == Material.AIR) {
                        b.setType(Material.WATER);
                    } else {
                        break;
                    }
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("EAST")) {
                    Block b = placedBlock.getLocation().add(i, 0, 0).getBlock();
                    if (b.getType() == Material.AIR) {
                        b.setType(Material.WATER);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
