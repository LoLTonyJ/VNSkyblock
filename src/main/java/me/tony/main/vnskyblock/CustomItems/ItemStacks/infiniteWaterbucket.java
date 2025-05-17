package me.tony.main.vnskyblock.CustomItems.ItemStacks;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class infiniteWaterbucket {

    public static ItemStack waterbucket() {

        ItemStack item = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bInfiniWater"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bA Waterbucket that has unlimited uses!"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.LEGENDARY);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Infinite_Water");

        return item;
    }

    public static ItemStack waterbucketPurchase() {

        ItemStack item = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bInfiniWater"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bA Waterbucket that has unlimited uses!"));
        lore.add(" ");
        lore.add(ChatColor.format("&6Cost: &b1000 Gems"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.LEGENDARY);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Infinite_Water");

        return item;
    }
}
