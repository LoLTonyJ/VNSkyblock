package me.tony.main.vnskyblock.CustomItems.ItemStacks;

import me.tony.main.vnskyblock.Util.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class teleportStick {

    public static ItemStack transmuteEnderpearl() {

        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&5Transmute Enderpearl"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&5Harvest the power of the pearl!"));
        lore.add(ChatColor.format("&7Upgrades Distance by &b+2 Blocks &cMax 8"));

        meta.setLore(lore);

        item.setItemMeta(meta);

        rarityUtil.setRarity(item, rarityUtil.Rarity.SPECIAL);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Transmute_Enderpearl");

        return item;
    }

    public static ItemStack transmuteEnderpearlPurchase() {

        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&5Transmute Enderpearl"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&5Harvest the power of the pearl!"));
        lore.add(ChatColor.format("&7Upgrades Distance by &b+2 Blocks &cMax 8"));
        lore.add(" ");
        lore.add(ChatColor.format("&6Cost: &b200 Gems"));

        meta.setLore(lore);

        item.setItemMeta(meta);

        rarityUtil.setRarity(item, rarityUtil.Rarity.SPECIAL);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Transmute_Enderpearl");

        return item;
    }

    public static ItemStack tpStick() {

        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&5Teleport Stick"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&5Ability to teleport to anywhere!"));
        lore.add(ChatColor.format("&7Distance can be upgraded with &cTransmute Enderpearl"));
        lore.add(" ");
        lore.add(ChatColor.format("&6Upgrades: &5" + PDCUtil.itemNumberValue(item, Keys.UPGRADE_COUNTER) +"/4"));

        meta.setLore(lore);

        item.setItemMeta(meta);

        rarityUtil.setRarity(item, rarityUtil.Rarity.SPECIAL);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Teleport_Stick");

        return item;

    }

    public static ItemStack tpStickPurchase() {

        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&5Teleport Stick"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&5Ability to teleport to anywhere!"));
        lore.add(ChatColor.format("&7Distance can be upgraded with &cTransmute Enderpearl"));
        lore.add(" ");
        lore.add(ChatColor.format("&6Cost: &b5000 Gems"));

        meta.setLore(lore);

        item.setItemMeta(meta);

        rarityUtil.setRarity(item, rarityUtil.Rarity.SPECIAL);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Teleport_Stick");

        return item;

    }

}
