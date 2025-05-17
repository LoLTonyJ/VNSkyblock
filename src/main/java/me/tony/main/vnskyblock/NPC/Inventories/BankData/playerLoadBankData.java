package me.tony.main.vnskyblock.NPC.Inventories.BankData;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerLoadBankData implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FileManipulation.loadPlayerBankData(p);
    }

}
