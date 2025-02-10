package me.tony.main.vnskyblock.PlayerLevel;

import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatFormat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        int level = playerManager.getLevel(p);

        e.setFormat(ChatColor.format("&7[&b⭐" + level + "&7] " + p.getDisplayName() + " &7> " + msg));

    }



}
