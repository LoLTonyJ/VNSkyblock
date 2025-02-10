package me.tony.main.vnskyblock.Minions.DataFile;

import me.tony.main.vnskyblock.Minions.Methods.MinionManager;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
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
import java.util.stream.Collectors;

public class FileManager {

    private static File file;
    private static YamlConfiguration config;

    public static void removeMinionData(UUID minionUUID) {
        if (config.getConfigurationSection("minion_storage").contains(minionUUID.toString())) {
            config.getConfigurationSection("minion_storage").set(minionUUID.toString(), null);
        }
    }

    public static void loadMinionStorage() {
        for (String key : config.getConfigurationSection("minion_storage").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            List<ItemStack> loadList = new ArrayList<>();
            for (Object obj : config.getList("minion_storage." + key)) {
                if (!(obj instanceof ItemStack)) continue;
                loadList.add((ItemStack) obj);
                MinionManager.updateMinionList(uuid, loadList);
            }
        }

    }

    public static void loadMinionList() {
        for (String key : config.getConfigurationSection("players_minions").getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            List<?> minionList = config.getList("players_minions."+uuid);
            if (minionList != null) {
               List<UUID> updatedList = minionList.stream().filter(item -> item instanceof String)
                        .map(item -> UUID.fromString((String) item))
                        .collect(Collectors.toList());
               PlayerMinionManager.updateListFromConfig(uuid, updatedList);
            }
        }
    }

    public static void saveMinionStorage(UUID minionUUID) {
        ConfigurationSection sect = config.getConfigurationSection("minion_storage");
        if (sect != null) {
            if (!sect.contains(minionUUID.toString())) {
                config.set("minion_storage." + minionUUID, MinionManager.getMinionStorage(minionUUID));
            } else {
                sect.set(minionUUID.toString(), MinionManager.getMinionStorage(minionUUID));
            }
        }
        Save();
    }

    public static void saveMinionList(Player p) {
        ConfigurationSection sect = config.getConfigurationSection("players_minions");
        if (sect != null) {
            if (!sect.contains(p.getUniqueId().toString())) {
                config.set("players_minions." + p.getUniqueId(), PlayerMinionManager.getPlayerList(p));
            } else {
                sect.set(p.getUniqueId().toString(), PlayerMinionManager.getPlayerList(p));
            }
            Save();
        }
    }

    public static void Save() {

        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_minions.yml");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void Load() throws IOException, InvalidConfigurationException {
        file = new File(VNSkyblock.getInstance().getDataFolder(), "PlayerData/player_minions.yml");
        config = new YamlConfiguration();
        config.options().copyDefaults(true);
        if (!file.exists()) VNSkyblock.getInstance().saveResource("PlayerData/player_minions.yml", false);
        config.load(file);

    }





}
