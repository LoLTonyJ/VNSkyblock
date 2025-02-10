package me.tony.main.vnskyblock.Minions.Methods;

import me.tony.main.vnskyblock.Minions.DataFile.FileManager;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerMinionManager {

    private static HashMap<UUID, List<UUID>> playerMinionList = new HashMap<>();

    public static void updateListFromConfig(UUID uuid, List<UUID> list) {
        playerMinionList.put(uuid, list);
    }

    public static List<UUID> getPlayerList(Player p) {
        return playerMinionList.getOrDefault(p.getUniqueId(), new ArrayList<>());
    }

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
    @param
    ItemStack - Held / Targeted item.
    keyValue - Checks PDC key
     */
    public static boolean isMinionBlock(ItemStack itemStack, String keyValue) {
        return PDCUtil.itemContainsKey(Keys.ITEM_ID, itemStack) && PDCUtil.itemKeyValue(itemStack, Keys.ITEM_ID, keyValue);
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
            FileManager.removeMinionData(minionUUID);
        }
    }

}
