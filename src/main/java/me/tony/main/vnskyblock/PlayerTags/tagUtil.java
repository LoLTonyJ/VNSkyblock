package me.tony.main.vnskyblock.PlayerTags;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class tagUtil {

    private static HashMap<UUID, String> tagContent = new HashMap<>();
    private static HashMap<UUID, List<String>> ownedTags = new HashMap<>();

    public static String getPlayerTag(Player p) {
        if (tagContent.containsKey(p.getUniqueId())) {
            return tagContent.get(p.getUniqueId());
        }
        return "";
    }

    public static Integer playerTotalOwnedTags(Player p) {
        return ownedTags.get(p.getUniqueId()).size();
    }
}
