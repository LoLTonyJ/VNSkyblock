package me.tony.main.vnskyblock.IslandUtil;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.request.AddonRequestBuilder;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.database.objects.IslandDeletion;
import world.bentobox.bentobox.managers.IslandDeletionManager;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.*;
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

    public static void removePlayerIsland(Player player) {
        String skyblockWorldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");
        String spawnWorldName = VNSkyblock.getInstance().getConfig().getString("spawn_world");
        String prefix = VNSkyblock.getInstance().getConfig().getString("prefix");
        if (skyblockWorldName == null) {
            System.out.println("WorldName is null in config - IslandUtil L42");
            return;
        }
        IslandsManager manager = BentoBox.getInstance().getIslandsManager();
        World w = Bukkit.getWorld(skyblockWorldName);
        if (w == null) return;
        Island island = manager.getIsland(w, player.getUniqueId());
        if (hasIsland(player) && island != null) {
            if (manager.getIsland(w, player.getUniqueId()) != null && manager.isOwner(w, player.getUniqueId())) {
                if (manager.getIsland(w, player.getUniqueId()) != null) {
                    manager.deleteIsland(island, true, player.getUniqueId());
                    player.sendMessage(ChatColor.format(prefix + " &cYou have deleted your island! :("));
                    if (player.getWorld().getName().equals(skyblockWorldName)) {
                        if (spawnWorldName == null) return;
                        if (Bukkit.getWorld(spawnWorldName) == null) return;
                        World spawnWorld = Bukkit.getWorld(spawnWorldName);
                        if (spawnWorld == null) {
                            return;
                        }
                        Location loc = spawnWorld.getSpawnLocation();
                        player.teleport(loc);
                    }
                }
            }
        } else {
            player.sendMessage(ChatColor.format("&cYou do not have an Island to delete or you don't have permission!"));
        }
    }

    public static void addFlightDuration(Player p, Integer value) {
        if (hasFlightDuration(p)) {
            islandFlightDuration.put(p.getUniqueId(), flightDurationRemaining(p) + value);
        } else {
            islandFlightDuration.put(p.getUniqueId(), value);
        }
    }

    public static Set<UUID> getPlayerTeam(Player p) {
        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");
        IslandsManager islandsManager = BentoBox.getInstance().getIslandsManager();
        if (islandsManager.inTeam(Bukkit.getWorld(worldName), p.getUniqueId())) {
            return islandsManager.getMembers(Bukkit.getWorld(worldName), p.getUniqueId());
        }
        return null;
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
