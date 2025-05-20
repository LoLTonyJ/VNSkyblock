package me.tony.main.vnskyblock.NPC.Inventories;

import me.tony.main.vnskyblock.IslandUtil.IslandPlayerManager;
import me.tony.main.vnskyblock.NPC.Inventories.BankData.FileManipulation;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Bank {

    private static HashMap<UUID, Double> bankInformation = new HashMap<>();
    private static HashMap<Set<UUID>, Double> coopBankInformation = new HashMap<>();

    public static Double currentBankBalance(Player p) {
        return bankInformation.get(p.getUniqueId());
    }

    public static void withdrawPlayer(Player p, Double amount) {
        if (bankInformation.containsKey(p.getUniqueId())) {
            double currentAmount = bankInformation.get(p.getUniqueId());
            double updatedAmount = currentAmount - amount;
            bankInformation.replace(p.getUniqueId(), currentAmount, updatedAmount);
            FileManipulation.savePlayerBankData(p, updatedAmount);
        }
    }

    public static void depositPlayer(Player p, Double amount) {
        if (bankInformation.containsKey(p.getUniqueId())) {
            double currentAmount = bankInformation.get(p.getUniqueId());
            double updatedAmount = currentAmount + amount;
            bankInformation.replace(p.getUniqueId(), currentAmount, updatedAmount);
        } else {
            bankInformation.put(p.getUniqueId(), amount);
        }
        FileManipulation.savePlayerBankData(p, amount);
    }

    public static ItemStack exitOption() {
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&4&lExit"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack HundredpercentOption() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&6100% of current Coins"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack FiftypercentOption() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&650% of current Coins"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack TFpercentOption() {
        ItemStack item = new ItemStack(Material.EMERALD_ORE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&625% of current Coins"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack withdrawItem() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&6Withdraw Coins"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack depositItem() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&6Deposit Coins"));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack coopBankItem(Player p) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&6Co-Op Account"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&7Right-Click to open Bank Deposit/Withdraw Options"));
        lore.add(ChatColor.format("&aCurrent Amount > $" + coopBankInformation.get(IslandPlayerManager.getPlayerTeam(p))));
        lore.add(" ");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack personalBankItem(Player p) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&6Personal Account"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&7Right-Click to open Bank Deposit/Withdraw Options"));
        lore.add(ChatColor.format("&aCurrent Amount > $" + bankInformation.get(p.getUniqueId())));
        lore.add(" ");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static Inventory bankInventory(Player p) {

        Inventory inv = Bukkit.createInventory(p, 9*3, ChatColor.format("&6Bank"));
        inv.setItem(11, personalBankItem(p));
        inv.setItem(13, coopBankItem(p));
        inv.setItem(15, exitOption());

        p.openInventory(inv);


        return inv;
    }

    public static Inventory personalBankInventory(Player p) {

        Inventory inv = Bukkit.createInventory(p, 9*3, ChatColor.format("&6Personal Bank Options"));
        inv.setItem(11, depositItem());
        inv.setItem(13, withdrawItem());
        inv.setItem(15, exitOption());

        p.openInventory(inv);

        return inv;
    }


    public static Inventory bankDepositOptionsInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9*3, ChatColor.format("&6Deposit Coins"));
        inv.setItem(11, TFpercentOption());
        inv.setItem(13, FiftypercentOption());
        inv.setItem(15, HundredpercentOption());
        inv.setItem(22, exitOption());

        p.openInventory(inv);

        return inv;
    }

    public static Inventory bankWithdrawOptionsInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9*3, ChatColor.format("&6Withdraw Coins"));
        inv.setItem(11, TFpercentOption());
        inv.setItem(13, FiftypercentOption());
        inv.setItem(15, HundredpercentOption());
        inv.setItem(22, exitOption());

        p.openInventory(inv);

        return inv;
    }


}
