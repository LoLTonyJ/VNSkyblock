package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.petBonus;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.Util.randomChance;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class breakBlockPetBonus implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        List<String> blockCheck = VNSkyblock.getInstance().getConfig().getStringList("log_check");

        if (petBonus.getBonus(p) == null) return;

        debug.print(petBonus.getBonus(p));
        if (petBonus.getBonus(p).contains("LOGGING")) {
            if (petBonus.getBonus(p).contains("COMMON") || petBonus.getBonus(p).contains("RARE") || petBonus.getBonus(p).contains("LEGENDARY")) {
                for (String s : blockCheck) {
                    if (b.getType().equals(Material.valueOf(s))) {
                        if (randomChance.chance(randomChance.randomNumber(1, 100), 25)) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(b.getType(), randomChance.randomNumber(1, 3)));
                        }
                    }
                }
            }
            if (petBonus.getBonus(p).contains("RARE") || petBonus.getBonus(p).contains("LEGENDARY")) {
                for (String s : blockCheck) {
                    if (b.getType().equals(Material.valueOf(s))) {
                        if (randomChance.chance(randomChance.randomNumber(1, 100), 15)) {
                            Set<Block> blockToBreak = new HashSet<>();
                            timberAbility(b, Material.valueOf(s), blockToBreak, 5);
                            for (Block toBreak : blockToBreak) {
                                toBreak.breakNaturally();
                            }
                       }
                    }
                }
            }
        }
    }


    private void timberAbility(Block b, Material material, Set<Block> blockToBreak, int limit) {
        if (blockToBreak.size() >= limit) return;
        if (!blockToBreak.contains(b) && b.getType() == material) {
            blockToBreak.add(b);

            timberAbility(b.getRelative(1, 0, 0), material, blockToBreak, limit);
            timberAbility(b.getRelative(-1, 0, 0), material, blockToBreak, limit);
            timberAbility(b.getRelative(0, 1, 0), material, blockToBreak, limit);
            timberAbility(b.getRelative(0, -1, 0), material, blockToBreak, limit);
            timberAbility(b.getRelative(0, 0, 1), material, blockToBreak, limit);
            timberAbility(b.getRelative(0, 0, -1), material, blockToBreak, limit);

        }
    }



}


