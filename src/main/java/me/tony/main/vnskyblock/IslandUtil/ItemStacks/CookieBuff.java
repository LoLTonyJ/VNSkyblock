package me.tony.main.vnskyblock.IslandUtil.ItemStacks;

import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CookieBuff {

    public static ItemStack biscuit() {
        ItemStack item = new ItemStack(Material.COOKIE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&dButter Biscuit"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&7Consume this special cookie to get various buffs!"));
        meta.setLore(lore);

        PDCUtil.setItemID(item, "buttery_biscuit");
        PDCUtil.addUUID(item);

        item.setItemMeta(meta);
        return item;
    }


}
