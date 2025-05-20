package me.tony.main.vnskyblock.Admin.PlayerManager;

import me.tony.main.vnskyblock.Admin.FileManipulation.bannedPlayers;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class PunishManager implements Listener {

    public static ArrayList<Player> frozenPlayers = new ArrayList<>();

    @EventHandler
    public static void onLeave(PlayerQuitEvent e) {
        boolean banOnLeave = VNSkyblock.getInstance().getConfig().getBoolean("ban_on_leave");
        List<String> banReason = VNSkyblock.getInstance().getConfig().getStringList("ban_message");
        Player p = e.getPlayer();

        if (isFrozen(p)) {
            bannedPlayers.addPlayerBan(p, "Left Whilst Frozen", banReason);
        }
    }

    @EventHandler
    public static void onMove(PlayerMoveEvent e) {

        List<String> frozenMessage = VNSkyblock.getInstance().getConfig().getStringList("frozen_message_player");

        Player p = e.getPlayer();
        if (isFrozen(p)) {
            for (String s : frozenMessage) {
                p.sendMessage(ChatColor.format(s));
            }
        }

    }


    public static boolean isFrozen(Player player) {
        return frozenPlayers.contains(player);
    }

    public static void freezePlayer(Player player, Player staff) {

        List<String> frozenMessage = VNSkyblock.getInstance().getConfig().getStringList("frozen_message_player");

        if (frozenPlayers.contains(player)) {
            frozenPlayers.remove(player);
            staff.sendMessage(ChatColor.format("&aYou have unfrozen " + player.getDisplayName()));
        }
        frozenPlayers.add(player);
        staff.sendMessage(ChatColor.format("&cYou have frozen " + player.getDisplayName()));
        for (String s : frozenMessage) {
            player.sendMessage(ChatColor.format(s));
        }
    }

}
