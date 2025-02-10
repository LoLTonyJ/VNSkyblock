package me.tony.main.vnskyblock.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class rarityUtil {

    public enum Rarity {
        COMMON(org.bukkit.ChatColor.GRAY),
        UNCOMMON(org.bukkit.ChatColor.DARK_GRAY),
        RARE(org.bukkit.ChatColor.BLUE),
        LEGENDARY(org.bukkit.ChatColor.YELLOW),
        SPECIAL(org.bukkit.ChatColor.RED);

        private final org.bukkit.ChatColor color;
        Rarity(org.bukkit.ChatColor color) {
            this.color = color;
        }

        public org.bukkit.ChatColor getColor() {
            return color;
        }
    }

    public static void setRarity(ItemStack item, Rarity rarity) {
        if (item == null || item.getType().equals(Material.AIR)) return;
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() != null) {
            List<String> lore = meta.getLore();
            lore.add(" ");
            lore.add(rarity.getColor() + ChatColor.format("&l") + rarity.name());
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }



}
