package me.tony.main.vnskyblock.Admin.BlockPackage;

import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class upgradeAnvilItem {

    public static ItemStack upgradeAnvil() {
        ItemStack item = new ItemStack(Material.ANVIL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&7Upgrade Anvil"));
        PDCUtil.setItemID(item, "Upgrade_Anvil");
        item.setItemMeta(meta);
        return item;
    }


}
