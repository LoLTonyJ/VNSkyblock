package me.tony.main.vnskyblock.CustomItems.Events;

import me.tony.main.vnskyblock.CustomItems.Inventories.upgradeTPStick;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.direction;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportStick implements Listener {

    @EventHandler
    public static void onItemClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.STICK) && PDCUtil.itemKeyValue(item, Keys.ITEM_ID, "Teleport_Stick")) {
            if (p.isSneaking() && e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                p.openInventory(upgradeTPStick.upgradeInv(p));
            }
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (direction.getCardinalDirection(p).equalsIgnoreCase("NORTH")) {
                    p.teleport(p.getLocation().add(0, 0, -4));
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("SOUTH")) {
                    p.teleport(p.getLocation().add(0, 0, 4));
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("WEST")) {
                    p.teleport(p.getLocation().add(-4, 0, 0));
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                }
                if (direction.getCardinalDirection(p).equalsIgnoreCase("EAST")) {
                    p.teleport(p.getLocation().add(4, 0, 0));
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }
    }


}
