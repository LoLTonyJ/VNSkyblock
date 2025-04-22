package me.tony.main.vnskyblock.Admin.FileManagement;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class spawnFile {

    private static File file;
    private static YamlConfiguration config;

    public static Location loadSpawnLocation() {
        ConfigurationSection sect = config.getConfigurationSection("spawn_location");
        String worldName = sect.getString("World");
        int x = sect.getInt("X");
        int y = sect.getInt("Y");
        int z = sect.getInt("Z");

        return new Location(Bukkit.getWorld(worldName), x, y, z, Float.parseFloat((String) sect.get("Pitch")), Float.parseFloat((String) sect.get("Yaw")));
    }

    public static void saveSpawnLocation(String world, Integer x, Integer y, Integer z, Float pitch, Float yaw) {
        ConfigurationSection sect = config.getConfigurationSection("spawn_location");
        sect.set("World", world);
        sect.set("X", x);
        sect.set("Y", y);
        sect.set("Z", z);
        sect.set("Pitch", pitch);
        sect.set("Yaw", yaw);
        Save();
    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "ServerData/server_spawn.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "ServerData/server_spawn.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("ServerData/server_spawn.yml", false);
        config.load(file);

    }




}
