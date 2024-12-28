package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class tagFile {

    private static File file;
    private static YamlConfiguration config;

    public static void loadOwnedTags() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");

        for (String key : config.getConfigurationSection("player_tags").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            List<String> importList = config.getStringList("player_tags." + uuid);
            tagUtil.setList(uuid, importList);
        }
    }

    public static void loadActiveTag(Player player) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");

        for (String key : config.getConfigurationSection("active_tags").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            if (player.getUniqueId() == uuid) {
                String tag = config.getConfigurationSection("active_tags").getString(player.getUniqueId().toString());
                tagUtil.setTag(player, tagList.Tags.valueOf(tag));
            }
        }
    }

    public static void saveActiveTag(Player p) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");

        ConfigurationSection sect = config.getConfigurationSection("active_tags");
        sect.set(p.getUniqueId().toString(), tagUtil.getActiveTag(p).toString());

        Save();

    }

    public static void saveOwnedTags(Player p) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "TagData/playertags.yml");

        ConfigurationSection sect = config.getConfigurationSection("player_tags");
        if (sect == null) return;
        sect.set(p.getUniqueId().toString(), tagUtil.playerOwnedTags(p));

        Save();
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
