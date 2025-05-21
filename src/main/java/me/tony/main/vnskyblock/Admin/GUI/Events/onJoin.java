package me.tony.main.vnskyblock.Admin.GUI.Events;

import me.tony.main.vnskyblock.Admin.PlayerManager.PunishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (PunishManager.isBanned(e.getPlayer())) {

        }
    }

}
