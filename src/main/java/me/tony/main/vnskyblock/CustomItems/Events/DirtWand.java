package me.tony.main.vnskyblock.CustomItems.Events;

import me.tony.main.vnskyblock.CustomItems.Cooldowns;
import me.tony.main.vnskyblock.Util.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;

public class DirtWand implements Listener {

    public BlockFace getBlockFace(Player player) {
        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
        if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
        Block targetBlock = lastTwoTargetBlocks.get(1);
        Block adjacentBlock = lastTwoTargetBlocks.get(0);
        return targetBlock.getFace(adjacentBlock);
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();
        Player p = e.getPlayer();
        Action a = e.getAction();
        if (b == null) return;
        if (e.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        if (a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.getInventory().getItemInMainHand().getType().equals(Material.STICK) && PDCUtil.itemContainsKey(Keys.ITEM_ID, p.getInventory().getItemInMainHand()) && PDCUtil.itemKeyValue(p.getInventory().getItemInMainHand(), Keys.ITEM_ID, "dirt_wand")) {
                if (!Cooldowns.containsPlayer(p)) {
                    if (getBlockFace(p) != null) {
                        if (VNSkyblock.getEconomy().getBalance(p) >= 1) {
                            Cooldowns.addPlayer(p);
                            VNSkyblock.getEconomy().withdrawPlayer(p, 1);
                            if (getBlockFace(p).equals(BlockFace.UP)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(0, 1, 0);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                            if (getBlockFace(p).equals(BlockFace.DOWN)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(0, -1, 0);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                            if (getBlockFace(p).equals(BlockFace.NORTH)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(0, 0, -1);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                            if (getBlockFace(p).equals(BlockFace.SOUTH)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(0, 0, 1);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                            if (getBlockFace(p).equals(BlockFace.EAST)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(1, 0, 0);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                            if (getBlockFace(p).equals(BlockFace.WEST)) {
                                Location bLoc = e.getClickedBlock().getLocation().add(-1, 0, 0);
                                if (bLoc.getBlock().getType().equals(Material.AIR)) {
                                    bLoc.getBlock().setType(Material.DIRT);
                                }
                            }
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR: &cSomething went wrong!! Check your balance, make sure you have 1 gold available!"));
                        }
                    }
                }
            }
        }
    }
}
