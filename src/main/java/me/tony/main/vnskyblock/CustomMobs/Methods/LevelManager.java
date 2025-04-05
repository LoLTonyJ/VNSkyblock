package me.tony.main.vnskyblock.CustomMobs.Methods;

import me.tony.main.vnskyblock.Util.randomNumber;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class LevelManager {

    private static HashMap<UUID, Integer> entLevel = new HashMap<>();

    public static Integer getEntLevel(UUID uuid) {
        return entLevel.getOrDefault(uuid, 1);
    }

    public static void setEntLevel(Entity ent, Integer level) {
        if (inList(ent.getUniqueId())) {
            entLevel.replace(ent.getUniqueId(), level);
        } else {
            entLevel.put(ent.getUniqueId(), level);
        }
    }
    public static boolean inList(UUID uuid) {
        return entLevel.containsKey(uuid);
    }
    public static void removeList(UUID uuid) {
        entLevel.remove(uuid);
    }
}
