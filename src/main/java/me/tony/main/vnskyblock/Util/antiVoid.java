package me.tony.main.vnskyblock.Util;

import me.tony.main.vnskyblock.Admin.Methods.Spawn;
import me.tony.main.vnskyblock.VNSkyblock;
import net.milkbowl.vault.economy.Economy;
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
                e.getEntity().teleport(Spawn.getSpawn());
                Player p = (Player) e.getEntity();
                Economy eco = VNSkyblock.getEconomy();
                if (eco.getBalance(p) != 0) {
                    double balance = eco.getBalance(p);
                    p.sendMessage(ChatColor.format("&cYou fell in the void! You lost -$" + balance/2));
                    eco.withdrawPlayer(p, eco.getBalance(p) / 2);
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
