package me.tony.main.vnskyblock.Util;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class itemMetaEditor {

    public static ItemStack colorChange(ItemStack item, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack nameChange(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format(name));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack loreEdit(ItemStack item, String string) {
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() != null) {
            List<String> lore = meta.getLore();
            lore.add(ChatColor.format(string));
            meta.setLore(lore);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.format(string));
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

}
