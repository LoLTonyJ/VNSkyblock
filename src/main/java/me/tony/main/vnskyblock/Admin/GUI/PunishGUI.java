package me.tony.main.vnskyblock.Admin.GUI;


import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PunishGUI {

    public static Inventory punishInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, "Punish GUI");

        for (ItemStack item : punishConfiguration.getTemplateList()) {
            if (item != null) {
                inv.addItem(item);
            }
        }


        player.openInventory(inv);
        return inv;
    }

}
