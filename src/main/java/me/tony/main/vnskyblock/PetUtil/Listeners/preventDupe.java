package me.tony.main.vnskyblock.PetUtil.Listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class preventDupe implements Listener {

    @EventHandler
    public void armorStandClick(PlayerInteractEntityEvent e) {
        Entity ent = e.getRightClicked();
        if (ent instanceof ArmorStand) {
            if (ent.getCustomName() != null) {
                e.setCancelled(true);
            }
        }
    }


}
