package me.tony.main.vnskyblock.Minions.Inventories.MinionFunctions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CreateBlockArea {

    public static void initBlockArea(Location loc) {

        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                Block b = loc.getWorld().getBlockAt(x + i, y - 1, z + j);
                b.setType(Material.COBBLESTONE);
            }
        }
    }

}
