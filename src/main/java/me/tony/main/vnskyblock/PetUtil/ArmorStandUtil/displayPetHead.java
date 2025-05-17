package me.tony.main.vnskyblock.PetUtil.ArmorStandUtil;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class displayPetHead {

    private static HashMap<UUID, UUID> armorDB = new HashMap<>();


    public static void floatLogic(ArmorStand armorStand) {
        new BukkitRunnable() {
            double angle = 0;

            @Override
            public void run() {
                if (armorStand.isDead() || !armorStand.isValid()) {
                    return;
                }
                double y = 0.1 * Math.sin(angle);
                angle += Math.PI / 30;
                armorStand.teleport(armorStand.getLocation().add(0, y, 0));
            }
        }.runTaskTimer(VNSkyblock.getInstance(), 0L, 1L);
    }

    public static ArmorStand getPlayerStand(Player p) {
        if (armorDB.containsKey(p.getUniqueId())) {
            UUID asUUID = armorDB.get(p.getUniqueId());
            for (Entity as : p.getWorld().getEntities()) {
                if (as.getUniqueId() == asUUID && as.getType().equals(EntityType.ARMOR_STAND)) {
                    return (ArmorStand) as;
                }
            }
        }
        return null;
    }

    public static void pluginReloadArmorStandRemove() {
        if (armorDB.isEmpty()) return;
        for (UUID playerUUID : armorDB.keySet()) {
            for (World serverWorlds : Bukkit.getServer().getWorlds()) {
                for (Entity armorStands : serverWorlds.getEntities()) {
                    if (armorStands.getType().equals(EntityType.ARMOR_STAND) && armorStands.getUniqueId().equals(armorDB.get(playerUUID))) {
                        armorStands.remove();
                        System.out.println("Removed Abandoned Armor Stands.");
                    }
                }
            }
        }
    }

    public static void relocateArmorstand(Player p) {
        if (!armorDB.containsKey(p.getUniqueId())) return;
        UUID asUUID = armorDB.get(p.getUniqueId());
        for (Entity as : p.getWorld().getEntities()) {
            if (as.getUniqueId() == asUUID) {
                Location petLocation = as.getLocation();
                Location pLocation = p.getLocation();
                double distance = pLocation.distance(petLocation);
                if (distance >= 4) {
                    as.teleport(pLocation);
                }
            }
        }
    }

    public static void removePetDisplay(Player player) {
        if (armorDB.containsKey(player.getUniqueId())) {
            UUID armorStand = armorDB.get(player.getUniqueId());
            for (Entity as : player.getNearbyEntities(5, 5, 5)) {
                if (as.getUniqueId() == armorStand && as.getType().equals(EntityType.ARMOR_STAND)) {
                    as.remove();
                    armorDB.remove(player.getUniqueId());
                }
            }
        }
    }


    public static ArmorStand petDisplay(Player p, ItemStack item, Location loc, World w) {
        ArmorStand armorStand = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);

        if (item == null) return armorStand;

        String petName = item.getItemMeta().getDisplayName();
        armorStand.setCustomName(ChatColor.format(petName));
        armorStand.getEquipment().setHelmet(item);

        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCanPickupItems(false);
        armorStand.setGravity(false);

        armorDB.put(p.getUniqueId(), armorStand.getUniqueId());
        displayPetHead.floatLogic(armorStand);
        return armorStand;
    }


}
