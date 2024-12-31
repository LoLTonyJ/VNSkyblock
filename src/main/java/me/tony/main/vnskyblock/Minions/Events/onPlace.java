package me.tony.main.vnskyblock.Minions.Events;

import me.tony.main.vnskyblock.Minions.Methods.MinionSpawn;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.PDCUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class onPlace implements Listener {

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        Block b = e.getBlock();
        Location loc = b.getLocation().add(0.5, 0, 0.5);
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item.getItemMeta() == null) return;

        if (PlayerMinionManager.hasLimit(p)) {
            p.sendMessage(chatUtil.format("&cYou have reached the maximum amount of Minions allowed!"));
            e.setCancelled(true);
            return;
        }

        if (PDCUtil.itemHasKey(Keys.ITEM_ID, item) && PDCUtil.itemStringKey(item, Keys.ITEM_ID, "Cobblestone_Minion")) {
            e.setCancelled(true);
            MinionSpawn.createCobbleMinion(loc, p.getWorld(), item);
            PlayerMinionManager.updateList(p, UUID.fromString(PDCUtil.getItemUUID(item)), "add");
            item.setAmount(item.getAmount() - 1);
        }
    }
}