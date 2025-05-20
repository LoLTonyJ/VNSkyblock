package me.tony.main.vnskyblock.CustomMobs.Mobs;

import me.tony.main.vnskyblock.CustomMobs.Methods.LevelManager;
import me.tony.main.vnskyblock.CustomMobs.Mobs.ArmorSets.SUSMiner;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class SuspiciousMiner {

    public static Zombie minerZombie(World world, Location location) {
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        zombie.setCustomNameVisible(true);
        LevelManager.setEntLevel(zombie, 35);


        // Logic
        zombie.setCanPickupItems(false);
        zombie.setCanBreakDoors(false);
        zombie.getEquipment().setHelmet(SUSMiner.susMinerHelmet());
        zombie.getEquipment().setChestplate(SUSMiner.susMinerChestPlate());
        zombie.getEquipment().setLeggings(SUSMiner.susMinerPants());
        zombie.getEquipment().setBoots(SUSMiner.susMinerBoots());

        return zombie;
    }
}
