package me.tony.main.vnskyblock.PlayerCommands.Methods;

import me.tony.main.vnskyblock.PlayerCommands.DataFile.backpackData;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class backPackMethods {

    private static HashMap<UUID, List<ItemStack>> playerStash = new HashMap<>();

    public static Inventory playerBackpack(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*3, chatUtil.format("&b" + player.getDisplayName() + "'s backpack"));
        List<ItemStack> playerStash = getPlayerBackpack(player);
        for (ItemStack i : playerStash) {
            if (i != null) {
                inv.addItem(i);
            }
        }
        player.openInventory(inv);
        return inv;
    }

    public static void resetBackpack(Player player) {
        playerStash.remove(player.getUniqueId());
    }

    public static void addListFromConfig(UUID uuid, List<ItemStack> toAdd) {
        playerStash.put(uuid, toAdd);
    }

    public static List<ItemStack> getPlayerBackpack(Player p) {
        List<ItemStack> newList = new ArrayList<>();
        return playerStash.getOrDefault(p.getUniqueId(), newList);
    }

    public static void updateBackpack(Player p, Inventory inv) {
        List<ItemStack> invContents = Arrays.asList(inv.getContents());
        playerStash.put(p.getUniqueId(), invContents);
        backpackData.saveBackPack(p);
    }
}
