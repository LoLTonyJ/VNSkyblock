package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.ArmorStandUtil.displayPetHead;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class relocatePet implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        displayPetHead.relocateArmorstand(e.getPlayer());
    }


}
