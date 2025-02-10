package me.tony.main.vnskyblock.IslandUtil;

import me.tony.main.vnskyblock.Admin.Methods.Spawn;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

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

        if (PlayerManager.hasIsland(p)) {
            e.setCancelled(true);
            p.performCommand(command);
        } else {
            p.teleport(Spawn.getSpawn());
            p.sendMessage(ChatColor.format("&cNo island found, please create one!"));
        }
    }



}
