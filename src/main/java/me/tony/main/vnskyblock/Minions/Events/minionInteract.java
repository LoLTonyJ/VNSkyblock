package me.tony.main.vnskyblock.Minions.Events;

import me.tony.main.vnskyblock.Minions.Inventories.MinionFunctions.MineBlock;
import me.tony.main.vnskyblock.Minions.Inventories.cobbleMinionStorage;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class minionInteract implements Listener {


    @EventHandler
    public void onInteract(EntityDamageByEntityEvent e) {
        ArmorStand as = (ArmorStand) e.getEntity();

        Player p = (Player) e.getDamager();

        if (PDCUtil.entityHasKey(Keys.ENTITY_ID, as)) {
            if (p.isSneaking()) {
                ItemStack i = as.getEquipment().getHelmet();
                if (i == null) {
                    p.sendMessage(chatUtil.format("&c&l&oITEM_ERROR &cPlease contact an Administrator!"));
                    return;
                }
                p.getInventory().addItem(i);
                as.remove();
                PlayerMinionManager.updateList(p, UUID.fromString(PDCUtil.getItemUUID(i)), "remove");
                return;
            }
            cobbleMinionStorage.minionStorage(p, MineBlock.getMinionStorage(as.getUniqueId()));
            e.setCancelled(true);
        }
    }
}
