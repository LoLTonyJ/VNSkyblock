package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        playerOwnedPets.initPetList(p);
    }



}
