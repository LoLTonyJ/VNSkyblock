package me.tony.main.vnskyblock.CustomItems.Inventories;

import me.tony.main.vnskyblock.Util.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
                        for (ItemStack tpStick : p.getInventory()) {
                            if (tpStick.getType().equals(Material.STICK) && PDCUtil.itemKeyValue(tpStick, Keys.ITEM_ID, "Teleport_Stick")) {
                                PDCUtil.itemNumberAdd(tpStick, Keys.UPGRADE_COUNTER, 1);
                            }
                        }
                        return;
                    }
                }
            }
        }


    }


}
