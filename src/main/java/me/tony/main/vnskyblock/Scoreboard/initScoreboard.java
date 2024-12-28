package me.tony.main.vnskyblock.Scoreboard;

import me.tony.main.vnskyblock.PlayerTags.tagFile;
import me.tony.main.vnskyblock.Tablist.tablistUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class initScoreboard implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        scoreboardUtil.initScoreboard(e.getPlayer());
        tablistUtil.setTabName(e.getPlayer());
        tagFile.loadActiveTag(e.getPlayer());
    }
}
