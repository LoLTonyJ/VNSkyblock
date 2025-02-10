package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class tagUtil {

    private static HashMap<UUID, String> playerTags = new HashMap<>();
    private static HashMap<UUID, List<String>> ownedTags = new HashMap<>();

    public static List<String> getCreatedTags() {
        return tagFile.loadCreatedServerTags();
    }

    public static boolean playerOwnsTag(String tag, Player p) {
        if (!ownedTags.containsKey(p.getUniqueId())) return false;
        List<String> playerTags = ownedTags.get(p.getUniqueId());
        return playerTags.contains(tag);
    }

    public static void setActiveTag(Player p, String tag, String action) {
        if (playerTags.containsKey(p.getUniqueId())) {
            if (action.equalsIgnoreCase("set")) {
                p.sendMessage(ChatColor.format("&cYou already have an active tag!"));
                return;
            }
            if (action.equalsIgnoreCase("remove")) {
                playerTags.remove(p.getUniqueId());
                p.sendMessage(ChatColor.format("&cYou have removed your active tag!"));
            }
        } else {
            if (action.equalsIgnoreCase("set")) {
                playerTags.put(p.getUniqueId(), tag);
            }
            if (action.equalsIgnoreCase("remove")) {
                playerTags.remove(p.getUniqueId());
            }
        }
    }

}
