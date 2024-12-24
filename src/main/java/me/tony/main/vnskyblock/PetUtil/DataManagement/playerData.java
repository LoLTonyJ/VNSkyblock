package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.debug;
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

public class playerData {
    private static File file;
    private static YamlConfiguration config;


    public static void loadPetList() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetPlayerData.yml");

        if (config.contains("player_data")) {
            for (String key : config.getConfigurationSection("player_data").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                for (Object obj : config.getList("player_data." + key)) {
                    if (!(obj instanceof ItemStack)) continue;
                    List<ItemStack> petList = new ArrayList<>();
                    petList.add((ItemStack) obj);
                    playerOwnedPets.PlayerPets.put(uuid, petList);
                }
            }
        }
    }

    public static void savePetLevel(Player p, List<String> list) {

    }

    public static void savePetList(Player p, List<ItemStack> list) {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetPlayerData.yml");
        ConfigurationSection section = config.getConfigurationSection("player_data");

        if (section != null) {
            if (section.contains(String.valueOf(p.getUniqueId()))) {
                section.set(p.getUniqueId().toString(), list);
                Save();
            } else {
                section.set(p.getUniqueId().toString(), list);
                Save();
            }
        } else {
            debug.print("null section");
        }


    }


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
