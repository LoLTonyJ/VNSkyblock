package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class tagFile {

    private static File file;
    private static YamlConfiguration config;


    public static List<String> loadCreatedServerTags() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        return config.getStringList("tag_list");
    }

    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("TagData/playertags.yml", false);
        config.load(file);

    }


}
