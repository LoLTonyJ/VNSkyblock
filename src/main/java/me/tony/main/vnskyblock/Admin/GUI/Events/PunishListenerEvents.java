package me.tony.main.vnskyblock.Admin.GUI.Events;

import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Admin.PlayerManager.MuteManager;
import me.tony.main.vnskyblock.Admin.PlayerManager.PunishManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class PunishListenerEvents implements Listener {

    @EventHandler
    public void playerIsBannedCheck(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (PunishManager.isBanned(p)) {
            String prefix = punishConfiguration.getConfigPathString("punish_prefix");
            p.kickPlayer(ChatColor.format(prefix + " \n" + "\n&7" + PunishManager.getBanReason(p).get(0) + "\n" + PunishManager.getBanReason(p).get(1) + "\n" + "\n" + " &7You can appeal here; \n" + punishConfiguration.getLinkContent()));
        }
    }

    @EventHandler
    public void playerIsMutedCheck(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (MuteManager.isMuted(p)) {
            e.setCancelled(true);
            for (String s : MuteManager.getMuteReason(p)) {
                p.sendMessage(ChatColor.format(s));
            }
        }
    }

}
