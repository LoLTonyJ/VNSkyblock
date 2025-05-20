package me.tony.main.vnskyblock.Admin.FileManipulation;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class punishConfiguration {

    private static File file;
    private static YamlConfiguration config;

    public static List<String> getReasonBuilder(String reason) {
        return config.getStringList("ban_reason_replace." + reason.toLowerCase());
    }

    public static void reloadPunishConfig() throws IOException, InvalidConfigurationException {
        config.load(file);
    }

    public static String getLinkContent() {
        if (getSetLink().equalsIgnoreCase("DISCORD")) {
            return (String) config.get("discord_invite_link");
        }
        if (getSetLink().equalsIgnoreCase("WEBSITE")) {
            return (String) config.get("website_invite_link");
        }
        return "INVITE_NULL";
    }

    public static String getSetLink() {
        return (String) config.get("invite_link_to_use");
    }

    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "punishconfig.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "punishconfig.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("punishconfig.yml", false);
        config.load(file);

    }

}
