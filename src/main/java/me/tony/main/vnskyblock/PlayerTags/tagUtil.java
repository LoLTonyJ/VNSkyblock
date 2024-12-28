package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class tagUtil {

    private static HashMap<UUID, tagList.Tags> playerTags = new HashMap<>();
    private static HashMap<UUID, List<String>> ownedTags = new HashMap<>();

    public static void setList(UUID uuid, List<String> list) {
        ownedTags.put(uuid, list);
    }

    public static void setTag(Player p, tagList.Tags tag) {
        playerTags.put(p.getUniqueId(), tag);
        tagFile.saveActiveTag(p);
    }

    public static tagList.Tags getActiveTag(Player p) {
        return playerTags.getOrDefault(p.getUniqueId(), null);
    }

    public static List<String> playerOwnedTags(Player p) {
        return ownedTags.get(p.getUniqueId());
    }

    public static void addTag(Player p, String tag) {
        if (ownedTags.containsKey(p.getUniqueId())) {
            List<String> list = playerOwnedTags(p);
            list.add(tag);
            ownedTags.put(p.getUniqueId(), list);
            p.sendMessage(chatUtil.format("&7You've added " + tag + " to " + p.getDisplayName() + "'s collection!"));
            tagFile.saveOwnedTags(p);
        }
        List<String> newList = new ArrayList<>();
        newList.add(tag);
        ownedTags.put(p.getUniqueId(), newList);
        p.sendMessage(chatUtil.format("&7You've added " + tag + " to " + p.getDisplayName() + "'s collection!"));
        tagFile.saveOwnedTags(p);
    }
}
