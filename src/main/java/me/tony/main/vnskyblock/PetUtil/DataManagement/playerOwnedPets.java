package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class playerOwnedPets {

    private static HashMap<UUID, List<ItemStack>> PlayerPets = new HashMap<>();

    public static void initPetList(Player p) {
        if (PlayerPets.containsKey(p.getUniqueId())) return;
        List<ItemStack> newList = new ArrayList<>();
        newList.add(petItems.RockPet(rarityUtil.Rarity.COMMON, 25));
        PlayerPets.put(p.getUniqueId(), newList);

    }

    public static void addPet(Player p, ItemStack item) {
        List<ItemStack> pets = PlayerPets.computeIfAbsent(p.getUniqueId(), k -> new ArrayList<>());
        pets.add(item);
    }

    public static void removePet(Player p, ItemStack item) {
        List<ItemStack> pets = PlayerPets.get(p.getUniqueId());
        if (pets.contains(item)) {
            pets.remove(item);
            p.getInventory().addItem(item);
        }
    }

    public static List<ItemStack> getOwnedPets(Player p) {
        return PlayerPets.get(p.getUniqueId());
    }


}
