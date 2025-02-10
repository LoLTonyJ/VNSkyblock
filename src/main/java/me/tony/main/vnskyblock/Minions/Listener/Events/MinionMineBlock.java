package me.tony.main.vnskyblock.Minions.Listener.Events;

import me.tony.main.vnskyblock.Minions.DataFile.FileManager;
import me.tony.main.vnskyblock.Minions.Methods.MinionManager;
import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MinionMineBlock {

    public static void initMinionMine(Location minionLocation, ArmorStand minion) {

        Random random = new Random();

        new BukkitRunnable() {

            int x = (int) minionLocation.getX();
            int y = (int) minionLocation.getY();
            int z = (int) minionLocation.getZ();

            @Override
            public void run() {

                if (minion.isDead()) return;

                int dx = random.nextInt(5) - 2;
                int dz = random.nextInt(5) - 2;

                Block b = minionLocation.getWorld().getBlockAt(x + dx, y - 1, z + dz);
                if (b.getType() != Material.AIR) {
                    Material originalType = b.getType();
                    ItemStack toAdd = new ItemStack(b.getType());
                    List<ItemStack> minionStorage = MinionManager.getMinionStorage(minion.getUniqueId());
                    boolean found = false;
                    for (ItemStack i : minionStorage) {
                        if (i.getType() == toAdd.getType() && i.getAmount() != 64) {
                            i.setAmount(i.getAmount() + 1); // Update the existing ItemStack amount
                            MinionManager.updateMinionList(minion.getUniqueId(), minionStorage);
                            debug.print("Added to minion storage");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        minionStorage.add(toAdd);
                        MinionManager.updateMinionList(minion.getUniqueId(), minionStorage);
                        debug.print("Added item to new list");
                    }
                    b.setType(Material.AIR);
                    debug.print("mined block, check storage.");
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            b.setType(originalType);
                        }
                    }.runTaskLater(VNSkyblock.getInstance(), 100L);
                }
            }
        }.runTaskTimer(VNSkyblock.getInstance(), 200L,200L);
    }
}
