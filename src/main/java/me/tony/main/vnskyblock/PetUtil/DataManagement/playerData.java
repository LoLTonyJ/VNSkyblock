package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class playerData {
    private static File file;
    private static YamlConfiguration config;




    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetPlayerData.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetPlayerData.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PetPlayerData.yml", false);
        config.load(file);

    }

}
