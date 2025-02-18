package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class tagData {

    private static File file;
    private static YamlConfiguration config;

    public static void removeTag(String tag) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        ConfigurationSection sect = config.getConfigurationSection("tag_storage");
        sect.set(tag, null);
    }

    public static void createTag(String tag, String content) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        ConfigurationSection sect = config.getConfigurationSection("tag_storage");
        sect.set(tag, content);
    }

    public static Boolean isCreated(String tag) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        ConfigurationSection sect = config.getConfigurationSection("tag_storage");
        return sect.contains(tag);
    }

    public static String getTagContent(String tag) {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        ConfigurationSection sect = config.getConfigurationSection("tag_storage");

        if (sect.contains(tag)) {
            return (String) sect.get(tag);
        } else {
            return "Tag not found!";
        }
    }

    public static void loadFile() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");

        config.load(file);
    }


}
