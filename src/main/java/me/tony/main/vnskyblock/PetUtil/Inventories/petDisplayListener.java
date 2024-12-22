package me.tony.main.vnskyblock.PetUtil.Inventories;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.chatUtil;
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
            if (clickAction.isRightClick()) {
                playerOwnedPets.removePet(p, item);
                p.sendMessage(chatUtil.format("&cYou have removed " + item.getItemMeta().getDisplayName() + " &cfrom your pet list!"));
                petDisplay.petInventory(p);
            }
        }
    }



}
