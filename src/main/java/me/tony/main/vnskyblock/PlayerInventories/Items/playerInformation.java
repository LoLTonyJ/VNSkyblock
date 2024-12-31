package me.tony.main.vnskyblock.PlayerInventories.Items;

import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class playerInformation {

    public static ItemStack generalInformation() {

        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(chatUtil.format("&bPlayer Menu"));
        List<String> lore = new ArrayList<>();
        lore.add(chatUtil.format("&7View basic information about your"));
        lore.add(chatUtil.format("&7profile, pets, and access your backpack storage!"));
        lore.add(" ");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


}
