package me.tony.main.vnskyblock.Minions.Methods;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MinionManager {

    private static HashMap<UUID, List<ItemStack>> MinionStorage = new HashMap<>();


    public static List<ItemStack> getMinionStorage(UUID minionUUID) {
        return MinionStorage.getOrDefault(minionUUID, new ArrayList<>());
    }


}
