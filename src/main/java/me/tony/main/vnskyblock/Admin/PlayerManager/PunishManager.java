package me.tony.main.vnskyblock.Admin.PlayerManager;


import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PunishManager {

    private static File file;
    private static YamlConfiguration config;

    private static ArrayList<Player> toggledLogs = new ArrayList<>();
    private static ArrayList<Player> mutedPlayers = new ArrayList<>();

    public static boolean isBanned(Player player) {
        Set<String> bannedPlayers = config.getKeys(false);
        for (String playerName : bannedPlayers) {
            if (playerName.equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean logsToggled(Player player) {
        return toggledLogs.contains(player);
    }

    public static void togglePunishLogs(Player player) {
        if (toggledLogs.contains(player)) {
            toggledLogs.remove(player);
            player.sendMessage(ChatColor.format("&aYou have re-enabled Punishment Logs."));
        }
        toggledLogs.add(player);
        player.sendMessage(ChatColor.format("&cYou have disabled Punishment Logs"));
        player.sendMessage(ChatColor.format("&cPunishment Logs will be re-enabled next time you log on!"));
    }

    public static void kickPlayer(Player staff, Player victim, List<String> reason) {
        if (!victim.isOnline()) return;
        String prefix = punishConfiguration.getConfigPathString("punish_prefix");
        victim.kickPlayer(ChatColor.format(prefix + " \n" + "\n&7" + reason.get(0) + "\n" + reason.get(1)));

        // History garbage.
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
        HistoryManager.addHistoryInput((Player) victim, staff, "MUTE", reason, format.format(date));

    }

    public static void kickBanPlayer(Player victim) {
        if (victim.isOnline()) {
            victim.kickPlayer("You have been kicked!");
        }
    }

    public static String sendLogInformation(Player player, Player victim, String punishment) {
        if (permCheck.isHelper(player)) {
            if (!logsToggled(player)) {
                return ChatColor.format("&c&l!! LOG !! &7" + player.getName() + " has " + punishment + " " + victim.getName());
            }
        }
        return null;
    }

    public static void removeBanConsole(String player) {
        Set<String> bannedPlayers = config.getKeys(false);
        for (String playerNames : bannedPlayers) {
            if (playerNames.equalsIgnoreCase(player)) {
                config.set(player, null);
                System.out.println("Removed " + player + " ban.");
                Save();
            } else {
                System.out.println("Could not find " + player);
            }
        }
    }


    public static List<String> getBanReason(Player victim) {
        return config.getStringList(victim.getName() + ".Reason");
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

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
        HistoryManager.addHistoryInput((Player) victim, staff, "MUTE", reason, format.format(date));


        Save();
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
