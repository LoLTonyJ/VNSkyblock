package me.tony.main.vnskyblock.PlayerInventories.Items;

import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class backpackStorage {

    public static ItemStack backItem() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bBackpack Storage"));
        item.setItemMeta(meta);
        return item;
    }

}
