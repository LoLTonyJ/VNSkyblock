package me.tony.main.vnskyblock.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class rarityUtil {

    public enum Rarity {
        COMMON(ChatColor.GRAY),
        UNCOMMON(ChatColor.DARK_GRAY),
        RARE(ChatColor.BLUE),
        LEGENDARY(ChatColor.YELLOW),
        SPECIAL(ChatColor.RED);

        private final ChatColor color;
        Rarity(ChatColor color) {
            this.color = color;
        }

        public ChatColor getColor() {
            return color;
        }
    }

    public static void setRarity(ItemStack item, Rarity rarity) {
        if (item == null || item.getType().equals(Material.AIR)) return;
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() != null) {
            List<String> lore = meta.getLore();
            lore.add(" ");
            lore.add(rarity.getColor() + chatUtil.format("&l") + rarity.name());
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }



}
