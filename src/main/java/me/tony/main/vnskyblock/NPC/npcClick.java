package me.tony.main.vnskyblock.NPC;

import me.tony.main.vnskyblock.NPC.Inventories.ElizabethInventory;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class npcClick implements Listener {

    @EventHandler
    public void NPCClick(NPCRightClickEvent e) {
        NPC n = e.getNPC();
        Player p = e.getClicker();

        if (n.getName().equals(ChatColor.stripColor("<light_purple>Elizabeth"))) {
            ElizabethInventory.premiumInv(p);
        }
        p.sendMessage(ChatColor.stripColor(n.getName()));

    }



}
