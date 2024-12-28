package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PetUtil.ArmorStandUtil.displayPetHead;
import me.tony.main.vnskyblock.PetUtil.Inventories.petDisplay;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.Util.direction;
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
        String strippedTitle = e.getView().getTitle().strip();
        if (strippedTitle.contains("Pet list")) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equals(chatUtil.format("&b&lOwned Pets"))) {
            e.setCancelled(true);
            if (clickAction.isLeftClick()) {
                if (item != null) {
                    if (playerOwnedPets.getActivePet(p) == null) {
                        playerOwnedPets.activatePet(p, item);
                        if (direction.getCardinalDirection(p).equalsIgnoreCase("north")) {
                            displayPetHead.petDisplay(p, item, p.getLocation().add(0, -1, 1), p.getWorld());
                        } else if (direction.getCardinalDirection(p).equalsIgnoreCase("east")) {
                            displayPetHead.petDisplay(p, item, p.getLocation().add(-1, -1, 0), p.getWorld());
                        } else if (direction.getCardinalDirection(p).equalsIgnoreCase("south")) {
                            displayPetHead.petDisplay(p, item, p.getLocation().add(0, -1, -1), p.getWorld());
                        } else if (direction.getCardinalDirection(p).equalsIgnoreCase("west")) {
                            displayPetHead.petDisplay(p, item, p.getLocation().add(1, -1, 0), p.getWorld());
                        }
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
