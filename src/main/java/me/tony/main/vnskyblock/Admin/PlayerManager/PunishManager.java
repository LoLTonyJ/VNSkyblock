package me.tony.main.vnskyblock.Admin.PlayerManager;


import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PunishManager {

    private static File file;
    private static YamlConfiguration config;

    public static boolean isBanned(Player player) {
        Set<String> bannedPlayers = config.getKeys(false);
        for (String playerName : bannedPlayers) {
            return playerName.equalsIgnoreCase(player.getName());
        }
        return false;
    }

    public static void removeBan(Player staff, Player victim) {
        if (!isBanned(victim.getPlayer())) {
            staff.sendMessage(ChatColor.format("&c&l!! &cThat player is not banned!"));
            return;
        }
        config.set(victim.getName(), null);
        staff.sendMessage(ChatColor.format("&aUnbanned " + victim.getName()));
        Save();
    }

    public static void addBan(Player staff, OfflinePlayer victim, List<String> reason) {
        if (victim == null) {
            System.out.println("NULL PLAYER");
            return;
        }
        if (isBanned(victim.getPlayer())) {
            staff.sendMessage(ChatColor.format("&c&l!! &cThat player is already banned!"));
            staff.closeInventory();
        }
        config.set(victim.getName(), null);
        config.set(victim.getName() + ".UUID", victim.getUniqueId().toString());
        config.set(victim.getName() + ".Reason", reason);
        config.set(victim.getName() + ".Banned By", staff.getName());

        Save();
    }

    public static void addHistoryInput(Player staff, OfflinePlayer victim, String punishment, List<String> reason) {
        if (victim == null) {
            System.out.println("NULL PLAYER");
            return;
        }
        // To be continued.
        config.set(victim.getName(), null);
        config.set(victim.getName() + ".Punishment Type", punishment.toUpperCase());
        config.set(victim.getName() + ".Reason", reason);
        config.set(victim.getName() + ".Responsible Staff Member", staff.getName());
        saveHistoryFile();
    }

    public static void loadHistoryFile() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/PlayerHistory/PlayerHistoryDir.yml");
        YamlConfiguration historyConfig = new YamlConfiguration();
        historyConfig.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PunishedPlayers/PlayerHistory/PlayerHistoryDir.yml", false);
        config.load(file);
    }

    public static void saveHistoryFile() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/PlayerHistory/PlayerHistoryDir.yml");
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
