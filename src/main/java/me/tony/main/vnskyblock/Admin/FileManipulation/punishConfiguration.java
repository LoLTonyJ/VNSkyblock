package me.tony.main.vnskyblock.Admin.FileManipulation;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class punishConfiguration {

    private static File file;
    private static YamlConfiguration config;

    public static List<String> getReasonBuilder(String reason) {
        return config.getStringList("ban_reason_replace." + reason.toLowerCase());
    }

    public static List<ItemStack> getTemplateList() {
        ConfigurationSection templateSection = config.getConfigurationSection("ban_reason_replace");
        Set<String> keys = templateSection.getKeys(false);
        List<ItemStack> itemStacks = new ArrayList<>();
        for (String key : keys) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.GRAY + key);
                List<String> lore = templateSection.getStringList(key);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            itemStacks.add(item);
        }
        return itemStacks;
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
        if (getSetLink().equalsIgnoreCase("BOTH")) {
            ArrayList<String> links = new ArrayList<>();
            links.add((String) config.get("discord_invite_link"));
            links.add((String) config.get("website_invite_link"));
            for (String s : links) {
                return s;
            }
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
