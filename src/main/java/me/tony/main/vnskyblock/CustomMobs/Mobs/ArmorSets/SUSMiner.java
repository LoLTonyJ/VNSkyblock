package me.tony.main.vnskyblock.CustomMobs.Mobs.ArmorSets;

import me.tony.main.vnskyblock.Util.itemMetaEditor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SUSMiner {

    public static ItemStack susMinerHelmet() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta meta = item.getItemMeta();

        itemMetaEditor.colorChange(item, Color.TEAL);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack susMinerChestPlate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();

        itemMetaEditor.colorChange(item, Color.TEAL);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack susMinerPants() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta meta = item.getItemMeta();

        itemMetaEditor.colorChange(item, Color.TEAL);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack susMinerBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta meta = item.getItemMeta();

        itemMetaEditor.colorChange(item, Color.TEAL);
        meta.addEnchant(Enchantment.UNBREAKING, 3, true);
        item.setItemMeta(meta);
        return item;
    }

}



