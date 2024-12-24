package me.tony.main.vnskyblock.PetUtil.Inventories;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PetUtil.ArmorStandUtil.displayPetHead;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class petDisplayListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ClickType clickAction = e.getClick();
        ItemStack item = e.getCurrentItem();
        if (e.getView().getTitle().equals(chatUtil.format("&b&lOwned Pets"))) {
            e.setCancelled(true);
            if (clickAction.isLeftClick()) {
                if (item != null) {
                    if (playerOwnedPets.getActivePet(p) == null) {
                        playerOwnedPets.activatePet(p, item);
                        displayPetHead.petDisplay(p, item, p.getLocation(), p.getWorld());
                    } else {
                        displayPetHead.removePetDisplay(p);
                        playerOwnedPets.removeActivePet(p);
                    }
                }
            }
            if (clickAction.isRightClick()) {
                if (item != null) {
                    playerOwnedPets.removePet(p, item);
                    p.sendMessage(chatUtil.format("&cYou have removed " + item.getItemMeta().getDisplayName() + " &cfrom your pet list!"));
                    petDisplay.petInventory(p);
                }
            }
        }
    }



}
