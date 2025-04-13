package me.tony.main.vnskyblock.CustomItems.Inventories;


import me.tony.main.vnskyblock.CustomItems.ItemStacks.teleportStick;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.inventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class upgradeTPStick {

    public static ItemStack confirmItem() {
        ItemStack item = new ItemStack(Material.LIME_WOOL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&a&lUpgrade?"));
        item.setItemMeta(meta);

        return item;
    }

    public static Inventory upgradeInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.format("&dUpgrade Teleport Stick?"));
        inventoryUtil.FillInventory(Material.GRAY_STAINED_GLASS_PANE, inv);
        inv.setItem(11, p.getInventory().getItemInMainHand());
        inv.setItem(13, teleportStick.transmuteEnderpearl());
        inv.setItem(15, confirmItem());
        return inv;
    }


}
