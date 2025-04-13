package me.tony.main.vnskyblock.NPC.Inventories;

import me.tony.main.vnskyblock.CustomItems.ItemStacks.dirtWand;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.infiniteWaterbucket;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.teleportStick;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.waterPumpBlock;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.inventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ElizabethInventory {

    public static Inventory premiumInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.format("&dElizabeth Shop"));


        inv.setItem(10, dirtWand.dirtItemPurchase());
        inv.setItem(12, infiniteWaterbucket.waterbucketPurchase());
        inv.setItem(14, waterPumpBlock.waterPumpPurchase());
        inv.setItem(16, teleportStick.tpStickPurchase());


        inventoryUtil.FillInventory(Material.BLACK_STAINED_GLASS_PANE, inv);
        p.openInventory(inv);
        return inv;
    }

    public static Inventory subMenu(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.format("&dTeleport Stick"));

        inv.setItem(11, teleportStick.tpStickPurchase());
        inv.setItem(15, teleportStick.transmuteEnderpearlPurchase());

        inventoryUtil.FillInventory(Material.BLACK_STAINED_GLASS_PANE, inv);
        p.openInventory(inv);
        return inv;

    }

}
