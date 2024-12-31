package me.tony.main.vnskyblock.Minions.Inventories.MinionFunctions;

import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MineBlock {

    private static HashMap<UUID, List<ItemStack>> MinionStorage = new HashMap<>();

    public static List<ItemStack> getMinionStorage(UUID minionUUID) {
        return MinionStorage.getOrDefault(minionUUID, new ArrayList<>());
    }

    public static void addMinionStorage(UUID minionUUID, ItemStack item) {
        List<ItemStack> storage = getMinionStorage(minionUUID);
        storage.add(item);
        MinionStorage.put(minionUUID, storage);
    }

    public static void minionBlockMine(Location minionLocation, ArmorStand minion) {

        new BukkitRunnable() {

            int x = (int) minionLocation.getX();
            int y = (int) minionLocation.getY();
            int z = (int) minionLocation.getZ();

            @Override
            public void run() {
                if (minion.isDead()) return;
                for (int dx = -2; dx <= 2; dx++) {
                    for (int dz = -2; dz <= 2; dz++) {
                        Block b = minionLocation.getWorld().getBlockAt(x + dx, y - 1, z + dz);
                        if (b.getType() != Material.AIR) {
                            ItemStack toAdd = new ItemStack(Material.valueOf(String.valueOf(b.getType())));
                            addMinionStorage(minion.getUniqueId(), toAdd);
                            b.setType(Material.AIR);
                            debug.print("mined a block, check storage");
                            return;
                        }
                    }
                }
            }
        }.runTaskTimer(VNSkyblock.getInstance(), 100L, 100L);

    }


}
