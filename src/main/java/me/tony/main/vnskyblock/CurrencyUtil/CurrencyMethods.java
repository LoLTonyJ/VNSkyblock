package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class CurrencyMethods implements Listener {


    public static void removeCurrency(Player p, int amount) {

        double min = VNSkyblock.getInstance().getConfig().getDouble("min_amount");

        if (!p.isOnline()) {
            System.out.println("Offline Players are not yet Implemented!");
            return;
        }

        if (amount <= min) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, "Amount specified is less than min amount provided in Configuration!");
            return;
        }




    }



}
