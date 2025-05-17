package me.tony.main.vnskyblock.IslandUtil.Events;

import me.tony.main.vnskyblock.Util.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class BiscuitInteract implements Listener {

    public static HashMap<UUID, Integer> BiscuitList = new HashMap<>();

    @EventHandler
    public void onEat(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();

        if (item == null) return;
        if (PDCUtil.itemContainsKey(Keys.ITEM_ID, item) && PDCUtil.itemKeyValue(item, Keys.ITEM_ID, "buttery_biscuit")) {
            if (BiscuitList.containsKey(p.getUniqueId())) {
                int time = BiscuitList.get(p.getUniqueId());
                BiscuitList.replace(p.getUniqueId(), time, time + 5);
                item.setAmount(item.getAmount() - 1);
                p.playNote(p.getEyeLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.A));
                p.sendMessage(ChatColor.format("&d&lBISCUIT &7>> You've eaten a special biscuit!"));
            }
        }
    }

    public static void reduceTime() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (BiscuitList.isEmpty()) return;
                for (UUID playerUUID : BiscuitList.keySet()) {
                    if (BiscuitList.containsKey(playerUUID) && BiscuitList.get(playerUUID) != 0 || BiscuitList.get(playerUUID) > 0) {
                        int timeLeft = BiscuitList.get(playerUUID);
                        int newTime = timeLeft - 1;
                        if (newTime == 0) {
                            BiscuitList.remove(playerUUID);
                            Player p = Bukkit.getServer().getPlayer(playerUUID);
                            if (p != null && p.isOnline()) {
                                p.sendMessage(ChatColor.format("&d&lBISCUIT &7>> Your Biscuit buff has expired!"));
                            }
                        }
                        BiscuitList.replace(playerUUID, timeLeft, newTime);
                    }
                }
            }

        }.runTaskTimer(VNSkyblock.getInstance(), 60, 60);
    }
}
