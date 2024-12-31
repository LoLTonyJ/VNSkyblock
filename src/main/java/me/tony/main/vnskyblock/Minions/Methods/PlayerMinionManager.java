package me.tony.main.vnskyblock.Minions.Methods;

import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerMinionManager {

    private static HashMap<UUID, List<UUID>> playerMinionList = new HashMap<>();

    public static boolean hasLimit(Player p) {
        List<UUID> playerList = playerMinionList.getOrDefault(p.getUniqueId(), new ArrayList<UUID>());
        return playerList.size() >= 2;
    }

    public static void updateList(Player p, UUID itemUUID, String action) {
        if (action.equalsIgnoreCase("add")) {
            if (playerMinionList.containsKey(p.getUniqueId())) {
                List<UUID> playerList = playerMinionList.get(p.getUniqueId());
                playerList.add(itemUUID);
                playerMinionList.put(p.getUniqueId(), playerList);
                debug.print("added");
                return;
            }
            List<UUID> newList = new ArrayList<>();
            newList.add(itemUUID);
            playerMinionList.put(p.getUniqueId(), newList);
            debug.print("new list");
        }
        if (action.equalsIgnoreCase("remove")) {
            List<UUID> playerList = playerMinionList.get(p.getUniqueId());
            playerList.remove(itemUUID);
            playerMinionList.put(p.getUniqueId(), playerList);
        }
    }


}
