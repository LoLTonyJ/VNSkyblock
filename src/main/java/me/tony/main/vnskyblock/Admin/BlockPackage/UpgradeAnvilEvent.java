package me.tony.main.vnskyblock.Admin.BlockPackage;

import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class UpgradeAnvilEvent implements Listener {

    @EventHandler
    public static void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        if (permCheck.isAdmin(p)) {
            if (b.getType().equals(Material.ANVIL) && PDCUtil.itemContainsKey(Keys.ITEM_ID, p.getInventory().getItemInMainHand()) && PDCUtil.itemKeyValue(p.getInventory().getItemInMainHand(), Keys.ITEM_ID, "Upgrade_Anvil")) {
                b.setMetadata("Upgrade_Anvil", new FixedMetadataValue(VNSkyblock.getInstance(), true));
            }
        }
    }


}
