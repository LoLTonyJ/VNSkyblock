package me.tony.main.vnskyblock.PlayerInventories.Listeners;

import me.tony.main.vnskyblock.PlayerInventories.Items.playerInformation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        List<ItemStack> pInv = Arrays.asList(p.getInventory().getContents());
        if (pInv.contains(playerInformation.generalInformation())) return;
        p.getInventory().setItem(8, playerInformation.generalInformation());
    }
}
