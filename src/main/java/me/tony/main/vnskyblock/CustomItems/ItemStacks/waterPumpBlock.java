package me.tony.main.vnskyblock.CustomItems.ItemStacks;

import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class waterPumpBlock {

    public static ItemStack waterPump() {

        ItemStack item = new ItemStack(Material.DARK_PRISMARINE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bWater Pump"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bPumps water towards the direction you're facing!"));

        meta.setLore(lore);
        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.LEGENDARY);
        PDCUtil.setItemID(item, "Water_Pump");
        return item;
    }

    public static ItemStack waterPumpPurchase() {

        ItemStack item = new ItemStack(Material.DARK_PRISMARINE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bWater Pump"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bPumps water towards the direction you're facing!"));
        lore.add(" ");
        lore.add(ChatColor.format("&6Cost: &b1200 Gems"));

        meta.setLore(lore);
        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.LEGENDARY);
        PDCUtil.setItemID(item, "Water_Pump");
        return item;
    }

}
