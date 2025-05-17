package me.tony.main.vnskyblock.PlayerData;

import me.tony.main.vnskyblock.IslandUtil.PlayerManager;
import org.bukkit.entity.Player;

public class islandLevel {

    public static Long getLevel(Player p) {
        if (PlayerManager.hasIsland(p)) {
            return PlayerManager.getIslandLevel(p.getUniqueId());
        }
        return null;
    }

}
