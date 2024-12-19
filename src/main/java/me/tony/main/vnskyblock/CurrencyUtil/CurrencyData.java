package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.GemConomy;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class CurrencyData {

    private static File file;
    private static YamlConfiguration config;


    public static void loadCurrency() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");
        new YamlConfiguration(); // not ideal, but works.
        config = YamlConfiguration.loadConfiguration(file);

        if (config.contains("player_currency")) {
            for (String key : config.getConfigurationSection("player_currency").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                int balance = config.getInt("player_currency." + key);
                GemConomy.PlayerData.put(uuid, balance);
            }
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Config ERROR");
        }
    }


    public static void saveCurrency() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");
        config = new YamlConfiguration();

        config.createSection("player_currency");

        if (!GemConomy.PlayerData.isEmpty()) {
            for (UUID pUUID : GemConomy.PlayerData.keySet()) {
                int amount = GemConomy.PlayerData.get(pUUID);
                if (amount == 0) return;
                config.set("player_currency." + pUUID.toString(), amount);
            }
        }
        Save();

    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("CurrencyData.yml", false);
        config.load(file);

    }



}
