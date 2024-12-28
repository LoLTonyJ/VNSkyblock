package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.petBonus;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.debug;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class enchantmentPetBonus implements Listener {

    public static HashMap<UUID, Integer> petCooldown = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();

        List<String> checkList = VNSkyblock.getInstance().getConfig().getStringList("item_check");
        String prefix = VNSkyblock.getInstance().getConfig().getString("pet_prefix");


        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (playerOwnedPets.getActivePet(p) == null) return;
            if (petBonus.getBonus(p).contains("MINING") && petBonus.getBonus(p).contains("COMMON") || petBonus.getBonus(p).contains("RARE") || petBonus.getBonus(p).contains("LEGENDARY")) {
                if (petCooldown.containsKey(p.getUniqueId())) {
                    p.sendMessage(chatUtil.format("&cYour pet is tired...."));
                    return;
                }
                if (item.getType().equals(Material.AIR)) return;
                if (!item.hasItemMeta()) return;
                for (String heldItem : checkList) {
                    if (item.getType().equals(Material.valueOf(heldItem))) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta != null) {
                            if (meta.hasEnchant(Enchantment.EFFICIENCY)) {
                                int currentLevel = meta.getEnchantLevel(Enchantment.EFFICIENCY);
                                meta.addEnchant(Enchantment.EFFICIENCY, currentLevel + 1, true);
                                p.sendMessage(chatUtil.format(prefix + " &7You've modified your Efficiency Level!"));
                                item.setItemMeta(meta);
                                petCooldown.put(p.getUniqueId(), 120);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        int level = meta.getEnchantLevel(Enchantment.EFFICIENCY);
                                        meta.addEnchant(Enchantment.EFFICIENCY, level -1, true);
                                        item.setItemMeta(meta);
                                    }
                                }.runTaskTimer(VNSkyblock.getInstance(), 800, 1L);
                            } else {
                                meta.addEnchant(Enchantment.EFFICIENCY, 1, true);
                                p.sendMessage(chatUtil.format(prefix + " &7You've added Efficiency 1 to your pickaxe!"));
                                item.setItemMeta(meta);
                                petCooldown.put(p.getUniqueId(), 120);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        int level = meta.getEnchantLevel(Enchantment.EFFICIENCY);
                                        meta.addEnchant(Enchantment.EFFICIENCY, level -1, true);
                                    }
                                }.runTaskTimer(VNSkyblock.getInstance(), 800, 1L);
                            }
                        }
                    }
                }
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (petCooldown.containsKey(p.getUniqueId())) {
                            int timeLeft = petCooldown.get(p.getUniqueId());
                            if (timeLeft > 0) {
                                int newTime = timeLeft - 1;
                                petCooldown.put(p.getUniqueId(), newTime);
                                debug.print("reduced time");
                            }
                            if (timeLeft == 0) {
                                petCooldown.remove(p.getUniqueId());
                                p.sendMessage(chatUtil.format("&aYour pet is well rested!"));
                                debug.print("cleared");
                            }
                        }
                    }
                }.runTaskTimer(VNSkyblock.getInstance(), 20, 4800);
            }
        }
    }
}
