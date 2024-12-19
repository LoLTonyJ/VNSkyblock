package me.tony.main.vnskyblock.MOTD;

import me.tony.main.vnskyblock.Util.ChatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class InitMOTD implements Listener {

    @EventHandler
    public void serverPing(ServerListPingEvent e) {

        String motd = VNSkyblock.getInstance().getConfig().getString("motd");

        e.setMotd(ChatUtil.format(motd));
    }

}
