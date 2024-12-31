package me.tony.main.vnskyblock.CustomMobs.Methods;

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class LevelManager {

    private static HashMap<UUID, Integer> entLevel = new HashMap<>();

    public static Integer getEntLevel(UUID uuid) {
        return entLevel.getOrDefault(uuid, 1);
    }

    public static void setEntLevel(Entity ent, Integer level) {
        UUID uuid = ent.getUniqueId();
        entLevel.replace(uuid, entLevel.get(uuid), level);
    }
    public static boolean inList(UUID uuid) {
        return entLevel.containsKey(uuid);
    }
    public static void removeList(UUID uuid) {
        entLevel.remove(uuid);
    }
}
