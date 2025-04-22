package me.tony.main.vnskyblock.NPC.Inventories.Events;

import me.tony.main.vnskyblock.NPC.Inventories.Bank;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class bankEvents implements Listener {

    @EventHandler
    public static void onInvClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("&6Withdraw Coins")) {
            if (e.getSlot() == 11) {
                // 25%
                Double bankBalance = Bank.currentBankBalance(p);
                Double withdrawAmount = 0.25*bankBalance;
                Bank.withdrawPlayer(p, withdrawAmount);
                VNSkyblock.getEconomy().depositPlayer(p, withdrawAmount);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have withdrew &a$" + withdrawAmount + "&6, from your bank account!"));
            }
            if (e.getSlot() == 13) {
                // 50%
                Double bankBalance = Bank.currentBankBalance(p);
                Double withdrawAmount = 0.50*bankBalance;
                Bank.withdrawPlayer(p, withdrawAmount);
                VNSkyblock.getEconomy().depositPlayer(p, withdrawAmount);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have withdrew &a$" + withdrawAmount + "&6, from your bank account!"));
            }
            if (e.getSlot() == 15) {
                // 100%
                Double bankBalance = Bank.currentBankBalance(p);
                VNSkyblock.getEconomy().depositPlayer(p, bankBalance);
                Bank.withdrawPlayer(p, bankBalance);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have withdrew &a$" + bankBalance + "&6, from your bank account!"));
            }
        }

        if (e.getView().getTitle().equalsIgnoreCase("&6Deposit Coins")) {
            if (e.getSlot() == 11) {
                // 25% deposit
                Double coins = VNSkyblock.getEconomy().getBalance(p);
                Double depositAmount = 0.25*coins;
                Bank.depositPlayer(p, depositAmount);
                VNSkyblock.getEconomy().withdrawPlayer(p, depositAmount);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have deposited &a$" + depositAmount + "&6, into your bank account!"));
            }
            if (e.getSlot() == 13) {
                // 50% deposit
                Double coins = VNSkyblock.getEconomy().getBalance(p);
                Double depositAmount = 0.50*coins;
                Bank.depositPlayer(p, depositAmount);
                VNSkyblock.getEconomy().withdrawPlayer(p, depositAmount);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have deposited &a$" + depositAmount + "&6, into your bank account!"));
            }
            if (e.getSlot() == 15) {
                // 100% deposit
                Double coins = VNSkyblock.getEconomy().getBalance(p);
                Bank.depositPlayer(p, coins);
                VNSkyblock.getEconomy().withdrawPlayer(p, coins);
                p.closeInventory();
                p.sendMessage(ChatColor.format("&6You have deposited &a$" + coins + "&6, into your bank account!"));
            }
            if (e.getSlot() == 22) {
                p.openInventory(Bank.bankInventory(p));
            }
        }

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format("&6Personal Bank Options"))) {
            if (e.getSlot() == 11) {
                p.openInventory(Bank.bankDepositOptionsInventory(p));
            }
            if (e.getSlot() == 13) {
                p.openInventory(Bank.bankWithdrawOptionsInventory(p));
            }
            if (e.getSlot() == 15) {
                p.closeInventory();
            }
        }

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.format("&6Bank"))) {

            if (e.getSlot() == 11) {
                p.openInventory(Bank.personalBankInventory(p));
            }
            if (e.getSlot() == 13) {
                p.closeInventory();
                p.sendMessage(ChatColor.format("&bComing Soon!"));
            }
            if (e.getSlot() == 15) {
                p.closeInventory();
            }
        }


    }

}
