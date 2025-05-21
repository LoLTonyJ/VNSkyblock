package me.tony.main.vnskyblock.Admin.GUI;


import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PunishGUI {

    private static HashMap<Player, Player> tempPunishDB = new HashMap<>();

    public static void clearVictim(Player staff) {
        tempPunishDB.remove(staff);
    }

    public static Player getVictim(Player staff) {
        return tempPunishDB.get(staff);
    }
    public static void setVictim(Player staff, Player victim) {
        if (!tempPunishDB.containsValue(victim)) {
            tempPunishDB.put(staff, victim);
        } else {
            staff.sendMessage(ChatColor.format("&cPlayer is already being punished!"));
        }
    }

    public static ItemStack historyIconPlaceholder() {
        String itemStackType = punishConfiguration.getConfigPathString("history_icon");
        String itemDisplayName = punishConfiguration.getConfigPathString("history_icon_name");
        List<String> itemLore = punishConfiguration.getConfigPathStringList("history_icon_lore");

        ItemStack item = new ItemStack(Material.valueOf(itemStackType));
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(ChatColor.format(itemDisplayName));
        if (!itemLore.isEmpty()) {
            meta.setLore(itemLore);
        }
        List<String> lore = new ArrayList<>();
        lore.add("NULL LIST");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack muteIconPlaceholder() {
        String itemStackType = punishConfiguration.getConfigPathString("mute_icon");
        String itemDisplayName = punishConfiguration.getConfigPathString("mute_icon_name");
        List<String> itemLore = punishConfiguration.getConfigPathStringList("mute_icon_lore");

        ItemStack item = new ItemStack(Material.valueOf(itemStackType));
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(ChatColor.format(itemDisplayName));
        if (!itemLore.isEmpty()) {
            meta.setLore(itemLore);
        }
        List<String> lore = new ArrayList<>();
        lore.add("NULL LIST");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack kickIconPlaceholder() {
        String itemStackType = punishConfiguration.getConfigPathString("kick_icon");
        String itemDisplayName = punishConfiguration.getConfigPathString("kick_icon_name");
        List<String> itemLore = punishConfiguration.getConfigPathStringList("kick_icon_lore");

        ItemStack item = new ItemStack(Material.valueOf(itemStackType));
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(ChatColor.format(itemDisplayName));
        if (itemLore != null && !itemLore.isEmpty()) {
            meta.setLore(itemLore);
        }
        List<String> lore = new ArrayList<>();
        lore.add("NULL LIST");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack banIconPlaceholder() {
        String itemStackType = punishConfiguration.getConfigPathString("ban_icon");
        String itemDisplayName = punishConfiguration.getConfigPathString("ban_icon_name");
        List<String> itemLore = punishConfiguration.getConfigPathStringList("ban_icon_lore");

        ItemStack item = new ItemStack(Material.valueOf(itemStackType));
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(ChatColor.format(itemDisplayName));
        if (itemLore != null && !itemLore.isEmpty()) {
            meta.setLore(itemLore);
        }
        List<String> lore = new ArrayList<>();
        lore.add("NULL LIST");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack skullPlaceHolder(OfflinePlayer victim) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta == null) return item;
        meta.setOwningPlayer(victim);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&7Players Name -> " + victim.getName()));
        item.setItemMeta(meta);

        return item;
    }

    public static Inventory punishMuteInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.format(punishConfiguration.getConfigPathString("mute_gui_name")));

        for (ItemStack itemStack : punishConfiguration.getTemplateList("mute_reason_replace")) {
            inv.addItem(itemStack);
        }

        player.openInventory(inv);
        return inv;
    }

    public static Inventory punishKickInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.format(punishConfiguration.getConfigPathString("kick_gui_name")));

        for (ItemStack itemStack : punishConfiguration.getTemplateList("kick_reason_replace")) {
            inv.addItem(itemStack);
        }

        player.openInventory(inv);
        return inv;
    }

    public static Inventory punishBanInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.format(punishConfiguration.getConfigPathString("ban_gui_name")));

        for (ItemStack itemStack : punishConfiguration.getTemplateList("ban_reason_replace")) {
            inv.addItem(itemStack);
        }

        player.openInventory(inv);
        return inv;
    }


    public static Inventory punishMainInventory(Player player) {
        Inventory punishINV = Bukkit.createInventory(player, 9*5, ChatColor.format(punishConfiguration.getConfigPathString("main_punish_gui_name")));
        punishINV.setItem(13, skullPlaceHolder(getVictim(player)));
        punishINV.setItem(29, kickIconPlaceholder());
        punishINV.setItem(30, historyIconPlaceholder());
        punishINV.setItem(31, banIconPlaceholder());
        punishINV.setItem(32, new ItemStack(Material.TOTEM_OF_UNDYING));
        punishINV.setItem(33, muteIconPlaceholder());

        player.openInventory(punishINV);
        return punishINV;
    }

}
