package me.tony.main.vnskyblock.PetUtil.Inventories;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class playerPetDisplay {

    public static Inventory displayPlayerPets(Player staff, Player target) {

        Inventory inv = Bukkit.createInventory(staff, 54, chatUtil.format(target.getDisplayName() + "&b's Pet list"));
        List<ItemStack> ownedPets = playerOwnedPets.getOwnedPets(target);
        if (ownedPets != null) {
            for (ItemStack i : ownedPets) {
                inv.addItem(i);
            }
        }
        staff.openInventory(inv);
        return inv;
    }

}
