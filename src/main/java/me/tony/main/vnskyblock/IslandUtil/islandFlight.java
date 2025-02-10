package me.tony.main.vnskyblock.IslandUtil;

import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class islandFlight implements Listener {

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if (!p.getGameMode().equals(GameMode.CREATIVE) || !p.isFlying()) {
            if (!PlayerManager.playerInSkyblockWorld(p)) return;
            if (!PlayerManager.hasFlightDuration(p)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.format("&cYou have no fly time!"));
            } else {
                if (PlayerManager.islandTeam(p)) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(ChatColor.format("&aYou've enabled island flight!"));
                    return;
                } else {
                    p.sendMessage(ChatColor.format("&cYou cannot fly on this island!"));
                }
            }
        }
        p.setAllowFlight(false);
        p.setFlying(false);
        p.sendMessage(ChatColor.format("&cYou've disabled island flight!"));
    }


}
