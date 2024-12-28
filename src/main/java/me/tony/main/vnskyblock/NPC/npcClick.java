package me.tony.main.vnskyblock.NPC;

import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class npcClick implements Listener {

    @EventHandler
    public void NPCClick(NPCRightClickEvent e) {
        NPC n = e.getNPC();
        Player p = e.getClicker();

        if (npcGetter.hasName("Test", n)) {
            p.sendMessage("has name");
            return;
        }
        p.sendMessage("no name");

    }



}
