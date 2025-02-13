package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class tagData {

    private static File file;
    private static YamlConfiguration config;

    public static String getTagContent(String tag) {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/tagstorage.yml");
        ConfigurationSection sect = config.getConfigurationSection("tag_storage");

        if (sect.contains(tag)) {
            return (String) sect.get(tag);
        } else {
            return "Tag not found!";
        }
    }


}
