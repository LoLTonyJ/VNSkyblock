package me.tony.main.vnskyblock.Minions.Listener;

import me.tony.main.vnskyblock.Minions.Inventories.FetchMinionStorage;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class onInteract implements Listener {

    @EventHandler
    public void ArmorstandInteract(PlayerInteractAtEntityEvent e) {
        Entity ent = e.getRightClicked();
        if (ent.getType().equals(EntityType.ARMOR_STAND)) e.setCancelled(true);
    }

    @EventHandler
    public void onMinionInteract(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getDamager();
        Entity ent = e.getEntity();

        debug.print(ent.getType().toString());

        // to pick the minion up
        if (ent.getType() == EntityType.ARMOR_STAND && PDCUtil.entityHasKey(Keys.ENTITY_ID, ent)) {
            ArmorStand as = (ArmorStand) ent;
            e.setCancelled(true);
            if (p.isSneaking()) {
                if (PlayerMinionManager.playerOwnsMinion(p, as.getUniqueId())) { // Checks to see if they own the Minion.
                    if (as.getEquipment().getHelmet() == null) {
                        p.sendMessage(chatUtil.format("&c&lITEM_ERROR &cPlease contact and Administrator!"));
                        return;
                    }
                    ItemStack item = as.getEquipment().getHelmet();
                    p.getInventory().addItem(item);
                    PlayerMinionManager.updateList(p, as.getUniqueId(), "remove");
                    as.remove();
                    return;
                }
            }
            FetchMinionStorage.openMinionStorage(p, as);
        }
    }


}
