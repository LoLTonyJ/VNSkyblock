package me.tony.main.vnskyblock.Minions.Listener;

import me.tony.main.vnskyblock.Minions.DataFile.FileManager;
import me.tony.main.vnskyblock.Minions.Inventories.FetchMinionStorage;
import me.tony.main.vnskyblock.Minions.Methods.MinionManager;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class onInteract implements Listener {

    private final Map<Inventory, UUID> inventoryEntityMap = new HashMap<>();

    @EventHandler
    public void ArmorstandInteract(PlayerInteractAtEntityEvent e) {
        Entity ent = e.getRightClicked();
        if (ent.getType().equals(EntityType.ARMOR_STAND)) e.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();

        if (e.getView().getTitle().equals(ChatColor.format("&b" + p.getName() + "'s Minion Storage"))) {
            UUID uuid = inventoryEntityMap.get(inv);
            List<ItemStack> updatedList = new ArrayList<>();
            for (ItemStack i : inv.getContents()) {
                if (i != null && i.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                    updatedList.add(i);
                }
            }
            MinionManager.updateMinionList(uuid, updatedList);
            FileManager.saveMinionStorage(uuid);
            debug.print("Updated list");
        }
        inventoryEntityMap.remove(inv);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getView().getTitle().equals(ChatColor.format("&b" + e.getPlayer().getName() + "'s Minion Storage"))) {
            for (Entity ent : e.getPlayer().getNearbyEntities(4, 4, 4)) {
                if (ent.getType().equals(EntityType.ARMOR_STAND) && PDCUtil.entityContainsKey(Keys.ENTITY_ID, ent)) {
                    inventoryEntityMap.put(e.getInventory(), ent.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onMinionInteract(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getDamager();
        Entity ent = e.getEntity();

        debug.print(ent.getType().toString());

        // to pick the minion up
        if (ent.getType() == EntityType.ARMOR_STAND && PDCUtil.entityContainsKey(Keys.ENTITY_ID, ent)) {
            ArmorStand as = (ArmorStand) ent;
            e.setCancelled(true);
            Location minionLoc = as.getLocation();
            Location playerLoc = p.getLocation();
            if (playerLoc.distance(minionLoc) >= 4 && PlayerMinionManager.playerOwnsMinion(p, as.getUniqueId())) {
                p.sendMessage(ChatColor.format("&cPlease get closer to the minion!"));
                return;
            }
            if (p.isSneaking()) {
                if (PlayerMinionManager.playerOwnsMinion(p, as.getUniqueId())) { // Checks to see if they own the Minion.
                    if (as.getEquipment().getHelmet() == null) {
                        p.sendMessage(ChatColor.format("&c&lITEM_ERROR &cPlease contact and Administrator!"));
                        return;
                    }
                    ItemStack item = as.getEquipment().getHelmet();
                    p.getInventory().addItem(item);
                    PlayerMinionManager.updateList(p, as.getUniqueId(), "remove");
                    as.remove();
                    FileManager.saveMinionList(p);
                    return;
                }
            }
            FetchMinionStorage.openMinionStorage(p, as);
        }
    }


}
