package me.tony.main.vnskyblock.PetUtil.Listeners;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class breakBlockPetBonus implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        if (playerOwnedPets.getActiveBonus(p) == null);

        debug.print(playerOwnedPets.getActiveBonus(p));
    }


}
