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


    public static void loadPetExperience() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");
        ConfigurationSection section = config.getConfigurationSection("pet_experience");

        // PetData -> pet_experience


    }

    public static void savePetExperience(UUID pet, Integer experience) {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");
        ConfigurationSection section = config.getConfigurationSection("pet_experience");

        if (section != null) {
            if (section.contains(pet.toString())) {
                section.set(pet.toString(), experience);
            }
            section.set(pet.toString(), experience);
        }

        Save();
    }

    public static void loadPetList() {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");

        if (config.contains("player_data")) {
            for (String key : config.getConfigurationSection("player_data").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                List<ItemStack> petList = new ArrayList<>();
                for (Object obj : config.getList("player_data." + key)) {
                    if (!(obj instanceof ItemStack)) continue;
                    petList.add((ItemStack) obj);
                    playerOwnedPets.setList(uuid, petList);
                }
            }
        }
    }

    public static void savePetList(Player p, List<ItemStack> list) {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");
        ConfigurationSection section = config.getConfigurationSection("player_data");

        if (section != null) {
            section.set(p.getUniqueId().toString(), list);
            Save();
        }


    }


    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PetData/PetPlayerData.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PetData/PetPlayerData.yml", false);
        config.load(file);

    }

}
