package me.tony.main.vnskyblock.CustomItems;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class Cooldowns {

    private static HashMap<UUID, Integer> cooldownList = new HashMap<>();

    public static void addPlayer(Player p) {
        UUID uuid = p.getUniqueId();
        if (cooldownList.containsKey(uuid)) return;
        cooldownList.put(uuid, 1);
    }

    public static boolean containsPlayer(Player p) {
        UUID uuid = p.getUniqueId();
        return cooldownList.containsKey(uuid);
    }

    public static void removeTime() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : cooldownList.keySet()) {
                    if (cooldownList.get(uuid) == 1) {
                        cooldownList.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(VNSkyblock.getInstance(), 20, 20);
    }

}
