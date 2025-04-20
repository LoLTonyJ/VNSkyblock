package me.tony.main.vnskyblock.PlayerCommands.Methods;

import me.tony.main.vnskyblock.PlayerCommands.DataFile.backpackData;
import me.tony.main.vnskyblock.PlayerCommands.DataFile.vaultData;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class vaultMethods {

    public static HashMap<UUID, List<ItemStack>>  storage = new HashMap<>();

    public static void updateBackpack(Player p, Inventory inv) {
        List<ItemStack> invContents = Arrays.asList(inv.getContents());
        storage.put(p.getUniqueId(), invContents);
        vaultData.savePlayerVaults(p);
    }

    public static List<ItemStack> addFromConfig(UUID pUUID, List<ItemStack> toAdd) {
        return storage.put(pUUID, toAdd);
    }

    public static List<ItemStack> getPlayerVault(Player p) {
        List<ItemStack> newList = new ArrayList<>();
        return storage.getOrDefault(p.getUniqueId(), newList);
    }

    public static void removePlayerVault(Player p) {
        if (getPlayerVault(p) != null) {
            storage.remove(p.getUniqueId());
        }
    }

    public static Inventory playerVault(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9*3, ChatColor.format("&b" + player.getDisplayName() + "'s vault"));
        List<ItemStack> playerStash = getPlayerVault(player);
        for (ItemStack i : playerStash) {
            if (i != null) {
                inv.addItem(i);
            }
        }
        player.openInventory(inv);
        return inv;
    }







}
