package me.tony.main.vnskyblock.Util;

import me.tony.main.vnskyblock.IslandUtil.IslandPlayerManager;
import me.tony.main.vnskyblock.VNSkyblock;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class antiVoid implements Listener {

    @EventHandler
    public void onVoid(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                e.setDamage(0);
                e.setCancelled(true);
                String configWorld = VNSkyblock.getInstance().getConfig().getString("spawn_world");
                if (Bukkit.getServer().getWorld(configWorld) != null) {
                    Location loc = Bukkit.getServer().getWorld(configWorld).getSpawnLocation();
                    e.getEntity().teleport(loc);
                    Player p = (Player) e.getEntity();
                    Economy eco = VNSkyblock.getEconomy();
                    if (eco.getBalance(p) != 0) {
                        double balance = eco.getBalance(p);
                        p.sendMessage(ChatColor.format("&cYou fell in the void! You lost -$" + balance/2));
                        eco.withdrawPlayer(p, eco.getBalance(p) / 2);
                    }
                } else {
                    if (IslandPlayerManager.hasIsland((Player) e.getEntity())) {
                        String command = VNSkyblock.getInstance().getConfig().getString("island_command");
                       ((Player) e.getEntity()).performCommand(command);
                    }
                }
            }
        }

    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
            }
        }
    }
}
