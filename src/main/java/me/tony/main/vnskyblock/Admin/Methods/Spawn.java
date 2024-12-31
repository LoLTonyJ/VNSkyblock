package me.tony.main.vnskyblock.Admin.Methods;

import me.tony.main.vnskyblock.Admin.FileManagement.spawnFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class Spawn {

    public static Location getSpawn() {
        return spawnFile.loadSpawnLocation();
    }

    public static void setSpawn(Player p) {
        String world = p.getWorld().getName();
        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();
        float pitch = p.getLocation().getPitch();
        float yaw = p.getLocation().getYaw();

        spawnFile.saveSpawnLocation(world, x, y, z, pitch, yaw);
    }


}
