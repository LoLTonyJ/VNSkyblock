package me.tony.main.vnskyblock.CustomItems.ItemStacks;

import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.chatUtil;
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

        meta.setDisplayName(chatUtil.format("&bWater Pump"));
        List<String> lore = new ArrayList<>();
        lore.add(chatUtil.format("&bPumps water towards the direction you're facing!"));

        meta.setLore(lore);
        item.setItemMeta(meta);
        rarityUtil.setRarity(item, rarityUtil.Rarity.LEGENDARY);
        PDCUtil.setItemID(item, "Water_Pump");
        return item;
    }

}
