package me.tony.main.vnskyblock.PlayerLevel;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class playerManager {

    private static HashMap<UUID, Integer> playerLevel = new HashMap<>();
    private static HashMap<UUID, Integer> playerLevelProgress = new HashMap<>();


    public static void setLevelFromConfig(UUID uuid, Integer level) {
        playerLevel.put(uuid, level);
    }

    public static Integer getLevel(Player p) {
        return playerLevel.getOrDefault(p.getUniqueId(), 0);
    }

    public static void setLevel(Player p, Integer level) {
        playerLevel.replace(p.getUniqueId(), getLevel(p), level);
        playerFile.savePlayerLevel(p);
    }

    public static void addLevel(Player p, Integer num) {
        int level = getLevel(p);
        playerLevel.put(p.getUniqueId(), level + num);
        playerFile.savePlayerLevel(p);
    }

    public static void resetLevel(Player p) {
        playerLevel.remove(p.getUniqueId());
    }

    public static Integer getProgress(Player p) {
        return playerLevelProgress.getOrDefault(p.getUniqueId(), 0);
    }

    public static void setProgress(Player p, Integer num) {
        int progress = getProgress(p);
        if (num > 500) return;
        playerLevelProgress.replace(p.getUniqueId(), playerLevelProgress.get(p.getUniqueId()), num);
    }

    public static void addProgress(Player p, Integer num) {
        int progress = getProgress(p);
        playerLevelProgress.put(p.getUniqueId(), progress + num);
    }

}
