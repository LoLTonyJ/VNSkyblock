package me.tony.main.vnskyblock.PetUtil.Inventories;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class petDisplay {

    public static Inventory petInventory(Player p) {
     Inventory inv = Bukkit.createInventory(p, 54, chatUtil.format("&b&lOwned Pets"));
     List<ItemStack> ownedPets = playerOwnedPets.getOwnedPets(p);
     if (ownedPets != null) {
         for (ItemStack i : ownedPets) {
             inv.addItem(i);
         }
     }
     p.openInventory(inv);
     return inv;
    }



}
