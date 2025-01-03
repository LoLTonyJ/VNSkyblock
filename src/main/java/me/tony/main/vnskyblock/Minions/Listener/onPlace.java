package me.tony.main.vnskyblock.Minions.Listener;

import me.tony.main.vnskyblock.Minions.Listener.Events.CreateMinionMineArea;
import me.tony.main.vnskyblock.Minions.Methods.MinionSpawn;
import me.tony.main.vnskyblock.Minions.Methods.PlayerMinionManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDCUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class onPlace implements Listener {

    @EventHandler
    public void onMinionPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        Block b = e.getBlock();
        Location bLoc = b.getLocation();
        World w = bLoc.getWorld();

        if (w == null) return;

        if (!PlayerMinionManager.hasLimit(player) && PlayerMinionManager.isMinionBlock(itemStack)) {
            e.setCancelled(true);
            MinionSpawn.createCobbleMinion(bLoc.add(0.5, 0,0.5), w, itemStack);
            for (Entity ent : bLoc.getWorld().getNearbyEntities(bLoc, 1, 1, 1)) {
                if (ent.getType().equals(EntityType.ARMOR_STAND) && PDCUtil.entityHasKey(Keys.ENTITY_ID, ent) && PDCUtil.entityStringKey(ent, Keys.ENTITY_ID, "Minion")) {
                    PlayerMinionManager.updateList(player, ent.getUniqueId(), "add");
                    itemStack.setAmount(itemStack.getAmount() - 1);
                    CreateMinionMineArea.create(b.getLocation());

                }
            }
        }



    }


}
