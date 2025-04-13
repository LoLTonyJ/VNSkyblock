package me.tony.main.vnskyblock.NPC.Inventories.Events;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.dirtWand;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.infiniteWaterbucket;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.teleportStick;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.waterPumpBlock;
import me.tony.main.vnskyblock.NPC.Inventories.ElizabethInventory;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class itemPurchase implements Listener {

    @EventHandler
    public static void onPurchase(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format("&dTeleport Stick"))) { // Sub Menu
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            Integer gemBalance = gemConomy.getBalance(p);
            if (e.getSlot() == 11) {
                // Stick Item
                if (gemBalance >= 5000) {
                    gemConomy.removeBalance(Bukkit.getConsoleSender(), p, 5000);
                    p.getInventory().addItem(teleportStick.tpStick());
                    p.sendMessage(ChatColor.format("&7Successfully purchase &BTeleport Stick&7 for &B5000 Gems!"));
                    ElizabethInventory.premiumInv(p);
                } else {
                    p.sendMessage(ChatColor.format("&c&LERROR! &cYou do not have enough gems!"));
                }
            }
            if (e.getSlot() == 15) {
                if (gemBalance >= 200) {
                    gemConomy.removeBalance(Bukkit.getConsoleSender(), p, 200);
                    p.getInventory().addItem(teleportStick.transmuteEnderpearl());
                    p.sendMessage(ChatColor.format("&7Successfully purchase &BTransmute Enderpearl&7 for &B200 Gems!"));
                } else {
                    p.sendMessage(ChatColor.format("&c&LERROR! &cYou do not have enough gems!"));
                }
            }
        }

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format("&dElizabeth Shop"))) { // Premium Shop
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            Integer gemBalance = gemConomy.getBalance(p);
            if (e.getSlot() == 10) {
                // Dirt Wand
                if (gemBalance >= 500) {
                    gemConomy.removeBalance(Bukkit.getConsoleSender(), p, 500);
                    p.getInventory().addItem(dirtWand.dirtItem());
                    p.sendMessage(ChatColor.format("&7Successfully purchase &BInfinite Dirt Wand&7 for &B500 Gems!"));
                } else {
                    p.sendMessage(ChatColor.format("&c&LERROR! &cYou do not have enough gems!"));
                }
            }
            if (e.getSlot() == 12) {
                // Infinite Water
                if (gemBalance >= 1000) {
                    gemConomy.removeBalance(Bukkit.getConsoleSender(), p, 1000);
                    p.getInventory().addItem(infiniteWaterbucket.waterbucket());
                    p.sendMessage(ChatColor.format("&7Successfully purchase &BInfinite Water Bucket&7 for &B1000 Gems!"));
                } else {
                    p.sendMessage(ChatColor.format("&c&LERROR! &cYou do not have enough gems!"));
                }
            }
            if (e.getSlot() == 14) {
                // Infinite Water
                if (gemBalance >= 1200) {
                    gemConomy.removeBalance(Bukkit.getConsoleSender(), p, 1200);
                    p.getInventory().addItem(waterPumpBlock.waterPump());
                    p.sendMessage(ChatColor.format("&7Successfully purchase &BWater Pump&7 for &B1200 Gems!"));
                } else {
                    p.sendMessage(ChatColor.format("&c&LERROR! &cYou do not have enough gems!"));
                }
            }
            if (e.getSlot() == 16) {
                ElizabethInventory.subMenu(p);
            }
        }
    }


}
