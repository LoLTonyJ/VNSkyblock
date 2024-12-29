package me.tony.main.vnskyblock.PlayerCommands.DataFile;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PlayerCommands.Methods.backPackMethods;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class backpackData {

    private static File file;
    private static YamlConfiguration config;


    public static void loadPlayerBackpacks() {
        for (String key : config.getConfigurationSection("player_backpacks").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            List<ItemStack> loadList = new ArrayList<>();
            for (Object obj : config.getList("player_backpacks." + key)) {
                if (!(obj instanceof ItemStack)) continue;
                loadList.add((ItemStack) obj);
                backPackMethods.addListFromConfig(uuid, loadList);
            }
        }
    }

    public static void saveBackPack(Player p) {

        ConfigurationSection sect = config.getConfigurationSection("player_backpacks");
        if (sect == null) return;

        sect.set(p.getUniqueId().toString(), backPackMethods.getPlayerBackpack(p));

        Save();

    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_backpacks.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_backpacks.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PlayerData/player_backpacks.yml", false);
        config.load(file);

    }
}
