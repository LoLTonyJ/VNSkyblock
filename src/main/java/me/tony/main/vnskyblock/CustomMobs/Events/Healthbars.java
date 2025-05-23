package me.tony.main.vnskyblock.CustomMobs.Events;

import me.tony.main.vnskyblock.CustomMobs.Methods.LevelManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.randomNumber;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftExperienceOrb;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftTextDisplay;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Healthbars implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {

        if (!(e.getEntity() instanceof LivingEntity)) return;
        LivingEntity ent = (LivingEntity) e.getEntity();
        int hp = (int) ent.getHealth();

        if (ent.getCustomName() != null) return;

        int level = randomNumber.generate(10, 1);
        LevelManager.setEntLevel(ent, level);

        if (LevelManager.getEntLevel(ent.getUniqueId()) > 5) {
            ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ent.getHealth() + 50);
            ent.setMaxHealth(ent.getHealth());
        }

        ent.setCustomName(ChatColor.format("&7[Lvl" + LevelManager.getEntLevel(ent.getUniqueId()) + "&7] &8" + ent.getName() + " &c" + hp + "/" + (int) ent.getMaxHealth()));
        ent.setCustomNameVisible(true);
    }

    @EventHandler
    public void onHit(EntityDamageEvent e) {
        LivingEntity ent = (LivingEntity) e.getEntity();

        if (ent instanceof ArmorStand) return;

        String name = ent.getType().toString();
        String output = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (ent.isDead()) {
            if (LevelManager.inList(ent.getUniqueId())) {
                LevelManager.removeList(ent.getUniqueId());
            }
        }
        int hp = (int) ent.getHealth();
        ent.setCustomName(ChatColor.format("&7[Lvl" + LevelManager.getEntLevel(ent.getUniqueId()) + "&7] &8" + output + " &c" + hp + "/" + (int) ent.getMaxHealth()));
    }


}
