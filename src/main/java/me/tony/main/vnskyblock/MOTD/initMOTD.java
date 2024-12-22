package me.tony.main.vnskyblock.MOTD;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class initMOTD implements Listener {

    @EventHandler
    public void serverPing(ServerListPingEvent e) {

        String motd = VNSkyblock.getInstance().getConfig().getString("motd");

        e.setMotd(chatUtil.format(motd));
    }

}
