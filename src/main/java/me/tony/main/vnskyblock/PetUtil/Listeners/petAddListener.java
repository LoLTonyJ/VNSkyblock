package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class petAddListener implements Listener {

    @EventHandler
    public void petClaim(PlayerInteractEvent e) {

        List<String> serverPets = VNSkyblock.getInstance().getConfig().getStringList("pet_names");

        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Action a = e.getAction();

        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.getItemMeta() == null || item.getType().equals(Material.AIR)) return;
            String strippedColor = item.getItemMeta().getDisplayName().strip().toLowerCase();
            for (String list : serverPets) {
                if (strippedColor.contains(list)) {
                    e.setCancelled(true);
                    playerOwnedPets.addPet(p, item);
                    playerOwnedPets.updatePetExperience(p, item, 0);
                    p.getInventory().remove(item);
                }
            }
        }


    }



}
