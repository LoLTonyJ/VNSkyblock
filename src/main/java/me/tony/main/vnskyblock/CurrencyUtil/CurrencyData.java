package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.GemConomy;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CurrencyData {

    private File file;
    private YamlConfiguration config;

    public void saveCurrency() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");
        config = new YamlConfiguration();

        if (!GemConomy.PlayerData.isEmpty()) {
            for (UUID pUUID : GemConomy.PlayerData.keySet()) {
                int amount = GemConomy.PlayerData.get(pUUID);
                if (amount == 0) return;
                config.set("player_currency.", pUUID.toString() + " : " + amount);
            }
        }

    }


    public void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "CurrencyData.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("CurrencyData.yml", false);
        config.load(file);

    }



}
