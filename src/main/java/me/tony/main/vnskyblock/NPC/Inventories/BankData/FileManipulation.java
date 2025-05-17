package me.tony.main.vnskyblock.NPC.Inventories.BankData;

import me.tony.main.vnskyblock.NPC.Inventories.Bank;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManipulation {

    private static File file;
    private static YamlConfiguration config;

    public static void loadPlayerBankData(Player p) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_banks.yml");
        ConfigurationSection sect = config.getConfigurationSection("players");
        UUID uuid = p.getUniqueId();
        if (sect.contains(uuid.toString())) {
            double amount = sect.getDouble(uuid.toString());
            Bank.depositPlayer(p, amount);
        }
    }

    public static void savePlayerBankData(Player p, Double amount) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_banks.yml");
        ConfigurationSection sect = config.getConfigurationSection("players");
        UUID uuid = p.getUniqueId();
        if (sect.contains(uuid.toString())) {
            sect.set(uuid.toString(), amount);
        }
        sect.set(uuid.toString(), amount);
        Save();
    }



    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_banks.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_banks.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PlayerData/player_banks.yml", false);
        config.load(file);

    }

}
