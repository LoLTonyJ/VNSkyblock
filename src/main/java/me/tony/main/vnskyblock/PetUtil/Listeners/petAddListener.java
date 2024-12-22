package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class petAddListener implements Listener {

    @EventHandler
    public void petClaim(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Action a = e.getAction();

        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            String strippedColor = item.getItemMeta().getDisplayName().strip();
            if (strippedColor.contains("Rock") || strippedColor.contains("Monkey")) {
                playerOwnedPets.addPet(p, item);
            } else {
                System.out.println("false");
            }
        }


    }



}
