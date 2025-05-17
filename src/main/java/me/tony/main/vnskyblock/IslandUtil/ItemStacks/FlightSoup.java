package me.tony.main.vnskyblock.IslandUtil.ItemStacks;

import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FlightSoup {

    public static ItemStack soup() {

        ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bMagical Stew"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&7Right-Click to gain 5 minutes of Island Flight"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        PDCUtil.setItemID(item, "magical_stew");

        return item;

    }


}
