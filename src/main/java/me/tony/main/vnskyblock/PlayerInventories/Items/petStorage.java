package me.tony.main.vnskyblock.PlayerInventories.Items;

import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class petStorage {

    public static ItemStack petItem() {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chatUtil.format("&bPet Storage"));
        item.setItemMeta(meta);
        return item;
    }

}
