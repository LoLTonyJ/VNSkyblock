package me.tony.main.vnskyblock.NPC;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class npcGetter {


    public static boolean hasName(String name, NPC n) {
        String toCheck = name.strip();
        String npcName = n.getName().strip();
        return npcName.equalsIgnoreCase(toCheck);
    }

    public static boolean isNPC(NPC n) {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.getName().equalsIgnoreCase(n.getName())) {
                return true;
            }
        }
        return false;
    }


}
