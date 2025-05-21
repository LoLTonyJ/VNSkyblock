package me.tony.main.vnskyblock.Admin.GUI.Events;

import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Admin.GUI.PunishGUI;
import me.tony.main.vnskyblock.Admin.PlayerManager.PunishManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ClickInventory implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        String title = e.getView().getTitle();
        if (title.equalsIgnoreCase(ChatColor.format(punishConfiguration.getConfigPathString("ban_gui_name")))) {
            PunishGUI.clearVictim(p);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Player victim = PunishGUI.getVictim(p);

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format(punishConfiguration.getConfigPathString("ban_gui_name")))) {
            e.setCancelled(true);
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem.getItemMeta() == null) return;
            String itemName = org.bukkit.ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            List<String> reason = punishConfiguration.getReasonBuilder("ban_reason_replace", itemName);
            PunishManager.addBan(p, victim, reason);
            p.closeInventory();
        }

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format(punishConfiguration.getConfigPathString("main_punish_gui_name")))) {
            e.setCancelled(true);
            if (victim == null) {
                p.closeInventory();
                p.sendMessage("NULL VICTIM");
                return;
            }
            if (e.getSlot() == 13) return;
            if (e.getSlot() == 29) {
                PunishGUI.punishKickInventory(p);
            }
            if (e.getSlot() == 30) return;
            if (e.getSlot() == 31) {
                PunishGUI.punishBanInventory(p);
            }
            if (e.getSlot() == 32) return;
            if (e.getSlot() == 33) {
                PunishGUI.punishMuteInventory(p);
            }
        }
    }


}
