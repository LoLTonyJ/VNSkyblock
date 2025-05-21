package me.tony.main.vnskyblock.Admin.FileManipulation;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class bannedPlayers {

    private static File file;
    private static YamlConfiguration config;

    public static void removePlayerBan(OfflinePlayer player, Player staff) {
        ConfigurationSection configSection = config.getConfigurationSection("player_information");
        if (configSection.contains(player.toString())) {
            configSection.set(player.toString(), null);
            staff.sendMessage(ChatColor.format("&aYou have unbanned " + player));
        }
        Save();
    }

    public static void addPlayerBan(OfflinePlayer player, String staff, List<String> reason) {
        ConfigurationSection configSection = config.getConfigurationSection("player_information");
        configSection.createSection(player.getName());
        ConfigurationSection playerSection = config.getConfigurationSection("player_information." + player.getName());
        if (playerSection != null) {
            playerSection.set("UUID : ", player.getUniqueId());
            playerSection.set("Reason : ", reason);
            playerSection.set("Banned By: ", staff);
        }
        Save();

    }

    public static boolean isBanned(Player player) {
        ConfigurationSection configSection = config.getConfigurationSection("player_information");
        UUID uuid = player.getUniqueId();
        return configSection.contains(uuid.toString());
    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/banned_players.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/banned_players.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PunishedPlayers/banned_players.yml", false);
        config.load(file);

    }

}
