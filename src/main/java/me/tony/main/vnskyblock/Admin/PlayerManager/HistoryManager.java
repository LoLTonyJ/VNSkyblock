package me.tony.main.vnskyblock.Admin.PlayerManager;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HistoryManager {

    private static File file;
    private static YamlConfiguration config;

    public static List<ItemStack> getHistory(Player victim) {
        ConfigurationSection historySection = config.getConfigurationSection(victim.getName());

        if (historySection == null) {
            return new ArrayList<>();
        }
        List<Map<?,?>> historyList = historySection.getMapList("History");
        List<ItemStack> itemStacks = new ArrayList<>();
        for (Map<?, ?> entry : historyList) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.RED + "Punishment: " + entry.get("Punishment"));
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Punished By: " + entry.get("Punished By"));
                lore.add(ChatColor.GRAY + "Date Issued: " + entry.get("Date Issued"));
                List<String> reason = (List<String>) entry.get("Reason for Punish");
                if (reason != null) {
                    for (String reasons : reason) {
                        lore.add(ChatColor.YELLOW + "- " + reasons);
                    }
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            itemStacks.add(item);
        }
        return itemStacks;
    }


    public static void addHistoryInput(Player victim, Player staff, String punishment, List<String> reason, Date date) {
        ConfigurationSection section = config.getConfigurationSection(victim.getName());
        Map<String, Object> entry = new HashMap<>();
        entry.put("Punishment", punishment);
        entry.put("Punished By", staff.getName());
        entry.put("Reason for Punish", reason);
        entry.put("Date Issued", date.toString());
        List<Map<?, ?>> history = section != null ? section.getMapList("History") : new ArrayList<>();
        history.add(entry);
        config.set(victim.getName() + ".History", history);

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
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PunishedPlayers/player_history.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PunishedPlayers/player_history.yml", false);
        config.load(file);

    }

}
