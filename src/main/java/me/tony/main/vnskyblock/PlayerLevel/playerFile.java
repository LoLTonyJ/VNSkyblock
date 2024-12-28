package me.tony.main.vnskyblock.PlayerLevel;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class playerFile {

    private static File file;
    private static YamlConfiguration config;


    public static void loadPlayerLevels() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_levels.yml");

        for (String key : config.getConfigurationSection("player_information").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            int level = config.getInt("player_information." + uuid);
            playerManager.setLevelFromConfig(uuid, level);
        }
    }

    public static void savePlayerLevel(Player p) {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_levels.yml");

        ConfigurationSection sect = config.getConfigurationSection("player_information");
        sect.set(p.getUniqueId().toString(), playerManager.getLevel(p));


        Save();
    }



    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_levels.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_levels.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PlayerData/player_levels.yml", false);
        config.load(file);

    }



}
