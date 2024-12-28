package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class currencyData {

    private static File file;
    private static YamlConfiguration config;


    public static void loadCurrency() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData/CurrencyData.yml");
        new YamlConfiguration(); // not ideal, but works.
        config = YamlConfiguration.loadConfiguration(file);

        if (config.contains("player_currency")) {
            for (String key : config.getConfigurationSection("player_currency").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                int balance = config.getInt("player_currency." + key);
                gemConomy.PlayerData.put(uuid, balance);
            }
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Config ERROR");
        }
    }


    public static void saveCurrency() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData/CurrencyData.yml");
        config = new YamlConfiguration();

        config.createSection("player_currency");

        if (!gemConomy.PlayerData.isEmpty()) {
            for (UUID pUUID : gemConomy.PlayerData.keySet()) {
                int amount = gemConomy.PlayerData.get(pUUID);
                if (amount == 0) return;
                config.set("player_currency." + pUUID.toString(), amount);
            }
        }
        Save();

    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData/CurrencyData.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData/CurrencyData.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("CurrencyData/CurrencyData.yml", false);
        config.load(file);

    }



}
