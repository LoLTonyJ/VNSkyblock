package me.tony.main.vnskyblock.IslandUtil;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.request.AddonRequestBuilder;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerManager {

    private static HashMap<UUID, Integer> islandFlightDuration = new HashMap<>();

    public static Integer flightDurationRemaining(Player p) {
        return islandFlightDuration.get(p.getUniqueId());
    }

    public static boolean hasFlightDuration(Player p) {
        return islandFlightDuration.containsKey(p.getUniqueId());
    }

    public static void removeFlightDuration(Player p, Integer value) {
        if (hasFlightDuration(p)) {
            if (flightDurationRemaining(p) <= 0) {
                islandFlightDuration.remove(p.getUniqueId());
            }
            islandFlightDuration.put(p.getUniqueId(), flightDurationRemaining(p) - value);
        }
    }

    public static void addFlightDuration(Player p, Integer value) {
        if (hasFlightDuration(p)) {
            islandFlightDuration.put(p.getUniqueId(), flightDurationRemaining(p) + value);
        } else {
            islandFlightDuration.put(p.getUniqueId(), value);
        }
    }

    public static boolean playerInSkyblockWorld(Player p) {
        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");
        return p.getWorld().equals(Bukkit.getWorld(worldName));
    }

    public static boolean islandTeam(Player p) {
        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");

        if (Bukkit.getWorld(worldName) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "World Does not exist!");
            return false;
        }
        IslandsManager islandsManager = BentoBox.getInstance().getIslandsManager();
        if (islandsManager.isOwner(Bukkit.getWorld(worldName), p.getUniqueId()) || islandsManager.inTeam(Bukkit.getWorld(worldName), p.getUniqueId())) {
            return true;
        }
        return false;
    }

    public static Long getIslandLevel(UUID playerUUID) {

        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");

        return (Long) new AddonRequestBuilder()
                .addon("Level")
                .label("island-level")
                .addMetaData("world", worldName)
                .addMetaData("player", playerUUID)
                .request();
    }

    public static boolean hasIsland(Player p) {

        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");

        if (Bukkit.getWorld(worldName) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "World Does not exist!");
            p.sendMessage(ChatColor.format("&cERROR! Contact an Administrator"));
            p.sendMessage(ChatColor.format("WORLD_NULL"));
            return false;
        }

        IslandsManager islandsManager = BentoBox.getInstance().getIslandsManager();
        World w = Bukkit.getWorld(worldName);
        if (islandsManager.hasIsland(w, p.getUniqueId()) || islandsManager.inTeam(w, p.getUniqueId())) {
            return true;
        }
        p.sendMessage(ChatColor.format("&cNo Island Found!"));
        return false;
    }

}
