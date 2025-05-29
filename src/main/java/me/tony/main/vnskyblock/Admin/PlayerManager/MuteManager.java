package me.tony.main.vnskyblock.Admin.PlayerManager;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MuteManager {

    private static File file;
    private static YamlConfiguration config;

    public static boolean isMuted(Player player) {
        Set<String> mutedPlayers = config.getKeys(false);
        for (String playerName : mutedPlayers) {
            if (playerName.equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void unmutePlayer(Player staff, Player victim) {
        if (victim == null) return;
        if (!isMuted(victim)) {
            staff.sendMessage(ChatColor.format("&c&l!! &cThat player is not muted!"));
        }
        config.set(victim.getName(), null);
        staff.sendMessage(ChatColor.format("&aUnmuted " + victim.getName()));
        Save();
    }

    public static void setMuted(Player staff, OfflinePlayer victim, List<String> reason) {

        if (victim == null) {
            System.out.println("NULL PLAYER");
            return;
        }
        if (isMuted(victim.getPlayer())) {
            staff.sendMessage(ChatColor.format("&c&l!! &cThat player is already muted!"));
            staff.closeInventory();
        }
        config.set(victim.getName(), null);
        config.set(victim.getName() + ".UUID", victim.getUniqueId().toString());
        config.set(victim.getName() + ".Reason", reason);
        config.set(victim.getName() + ".Muted By", staff.getName());

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
        HistoryManager.addHistoryInput((Player) victim, staff, "MUTE", reason, format.format(date));

        Save();
    }

    public static List<String> getMuteReason(Player victim) {
        return config.getStringList(victim.getName() + ".Reason");
    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/muted_players.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/muted_players.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PunishedPlayers/muted_players.yml", false);
        config.load(file);

    }


}
