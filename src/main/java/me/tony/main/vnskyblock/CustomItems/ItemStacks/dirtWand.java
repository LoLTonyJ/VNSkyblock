package me.tony.main.vnskyblock.CustomItems.ItemStacks;

import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class dirtWand {

    public static ItemStack dirtItem() {

        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.format("&bInfinite Dirt Wand"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&bA magical wand that never runs out of dirt!"));
        lore.add(" ");
        lore.add(ChatColor.format("&7Takes &61 Gold coin &7everytime you place dirt!"));
        meta.setLore(lore);

        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.RARE);
        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "dirt_wand");
        return item;
    }

}
