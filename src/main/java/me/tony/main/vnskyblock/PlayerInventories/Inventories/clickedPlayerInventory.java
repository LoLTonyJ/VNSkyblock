package me.tony.main.vnskyblock.PlayerInventories.Inventories;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PlayerCommands.Methods.vaultMethods;
import me.tony.main.vnskyblock.PlayerCommands.Methods.viewLoadout;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class clickedPlayerInventory implements Listener {

    @EventHandler
    public static void onClick(InventoryClickEvent e) {

        Player target = viewLoadout.viewList.get((Player) e.getWhoClicked());

        if (e.getView().getTitle().contains(ChatColor.stripColor("Loadout"))) {
            if (e.getSlot() == 23) {
                vaultMethods.getPlayerVault(target);
            }
            if (e.getSlot() == 41) {
                playerOwnedPets.getOwnedPets(target);
            }
        }
    }


}
