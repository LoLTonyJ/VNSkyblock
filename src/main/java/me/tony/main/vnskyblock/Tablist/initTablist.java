package me.tony.main.vnskyblock.Tablist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class initTablist implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        tablistUtil.initTab(e.getPlayer());


    }


}
