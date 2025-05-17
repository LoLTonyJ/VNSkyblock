package me.tony.main.vnskyblock.IslandUtil.Events;

import me.tony.main.vnskyblock.IslandUtil.PlayerManager;
import me.tony.main.vnskyblock.Util.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StewInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.getType().equals(Material.MUSHROOM_STEW)) return;

        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (PDCUtil.itemContainsKey(Keys.ITEM_ID, item) && PDCUtil.itemKeyValue(item, Keys.ITEM_ID, "magical_stew")) {
                item.setAmount(item.getAmount() -1);
                PlayerManager.addFlightDuration(p, 5);
                p.sendMessage(ChatColor.format("&cYou have added 5 minutes to your flight duration, you now have " + PlayerManager.flightDurationRemaining(p) + " minutes"));
            }
        }
    }


}
