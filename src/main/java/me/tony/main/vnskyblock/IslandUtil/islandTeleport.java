package me.tony.main.vnskyblock.IslandUtil;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.managers.IslandsManager;
import java.util.logging.Level;

public class islandTeleport implements Listener {


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        String spawnWorld = VNSkyblock.getInstance().getConfig().getString("spawn_world");
        String command = VNSkyblock.getInstance().getConfig().getString("island_command");

        Player p = e.getPlayer();
        Block b = p.getLocation().add(0, -1, 0).getBlock();
        World w = Bukkit.getWorld(spawnWorld);

        if (!b.getType().equals(Material.MAGENTA_STAINED_GLASS)) return;
        if (!p.getWorld().equals(w)) return;

        if (hasIsland(p)) {
            e.setCancelled(true);
            p.performCommand(command);
        }
    }


    public boolean hasIsland(Player p) {

        String worldName = VNSkyblock.getInstance().getConfig().getString("skyblock_world_name");

        if (Bukkit.getWorld(worldName) == null) {
            Bukkit.getLogger().log(Level.SEVERE, "World Does not exist!");
            p.sendMessage(chatUtil.format("&cERROR! Contact an Administrator"));
            p.sendMessage(chatUtil.format("WORLD_NULL"));
            return false;
        }

        IslandsManager islandsManager = BentoBox.getInstance().getIslandsManager();
        World w = Bukkit.getWorld(worldName);
        if (islandsManager.hasIsland(w, p.getUniqueId()) || islandsManager.inTeam(w, p.getUniqueId())) {
            return true;
        }
        p.sendMessage(chatUtil.format("&cNo Island Found!"));
        return false;
    }

}
