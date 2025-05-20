package me.tony.main.vnskyblock.PlayerData;

import me.tony.main.vnskyblock.IslandUtil.IslandPlayerManager;
import org.bukkit.entity.Player;

public class islandLevel {

    public static Long getLevel(Player p) {
        if (IslandPlayerManager.hasIsland(p)) {
            return IslandPlayerManager.getIslandLevel(p.getUniqueId());
        }
        return null;
    }

}
