package me.tony.main.vnskyblock.Tablist;

import me.tony.main.vnskyblock.PlayerLevel.playerManager;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static org.bukkit.Bukkit.getName;

public class tablistUtil {

    public static void setTabName(Player p) {
        p.setPlayerListName(chatUtil.format("&7[&b⭐" + playerManager.getLevel(p) + "&7] " + p.getDisplayName()));
    }


    public static void startTab() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Bukkit.getOnlinePlayers().isEmpty() || Bukkit.getOnlinePlayers().size() > 1) {
                        return;
                    } else {
                        initTab(p);
                    }
                }
            }
        }.runTaskTimer(VNSkyblock.getInstance(), 20, 20);
    }


    public static void initTab(Player p) {
        List<String> header = VNSkyblock.getInstance().getConfig().getStringList("tablist_header");
        List<String> footer = VNSkyblock.getInstance().getConfig().getStringList("tablist_footer");

        header.add(chatUtil.format( "&bOnline: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()));

        String joinedHeader = String.join("\n", header);
        String joinedFooter = String.join("\n", footer);

        p.setPlayerListHeader(chatUtil.format(joinedHeader));
        p.setPlayerListFooter(chatUtil.format(joinedFooter));


    }



}
