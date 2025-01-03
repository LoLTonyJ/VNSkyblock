package me.tony.main.vnskyblock.Minions.Methods;

import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerMinionManager {

    private static HashMap<UUID, List<UUID>> playerMinionList = new HashMap<>();

    // Checks the players List size.
    public static boolean hasLimit(Player p) {
        List<UUID> playerList = playerMinionList.getOrDefault(p.getUniqueId(), new ArrayList<UUID>());
        return playerList.size() >= 2;
    }
    /*
    @param
    Player - Player who owns the minion
    minionUUID - Entities UUID (ArmorStands UUID)
     */
    public static boolean playerOwnsMinion(Player p, UUID minionUUID) {
        List<UUID> minionList = playerMinionList.get(p.getUniqueId());
        return minionList.contains(minionUUID);
    }

    /*
    Checks the blocks PDC (PersistentDataContainer) and checks to see if it has the following keys, along with values.
     */
    public static boolean isMinionBlock(ItemStack itemStack) {
        return PDCUtil.itemHasKey(Keys.ITEM_ID, itemStack) && PDCUtil.itemStringKey(itemStack, Keys.ITEM_ID, "Cobblestone_Minion");
    }

    /*
    @param
    Player - Whoever placed the block
    minionUUID - Grab the entities UUID
    Action - Add/Remove UUID from the Players Minion List.
     */
    public static void updateList(Player player, UUID minionUUID, String action) {
        if (action.equalsIgnoreCase("add")) {
            if (playerMinionList.containsKey(player.getUniqueId())) {
                List<UUID> playerList = playerMinionList.get(player.getUniqueId());
                playerList.add(minionUUID);
                playerMinionList.put(player.getUniqueId(), playerList);
                debug.print("added");
                return;
            } else {
                List<UUID> newList = new ArrayList<>();
                newList.add(minionUUID);
                playerMinionList.put(player.getUniqueId(), newList);
                debug.print("new list");
            }
        }
        if (action.equalsIgnoreCase("remove")) {
            List<UUID> playerList = playerMinionList.get(player.getUniqueId());
            playerList.remove(minionUUID);
            playerMinionList.put(player.getUniqueId(), playerList);
        }
    }


}
