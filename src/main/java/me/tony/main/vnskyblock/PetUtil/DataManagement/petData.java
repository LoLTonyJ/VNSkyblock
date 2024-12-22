package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class petData {

    private static final HashMap<UUID, String> PetData = new HashMap<>();
    public static ArrayList<ItemStack> Pet = new ArrayList<>();

    public static void clickedPet(Player p, String petType) {
        petState(p, petType);
    }

    public static void initPetList() {
        Pet.add(petItems.RockPet(rarityUtil.Rarity.COMMON, 1));
        Pet.add(petItems.RockPet(rarityUtil.Rarity.RARE, 1));
        Pet.add(petItems.RockPet(rarityUtil.Rarity.LEGENDARY, 1));
        Pet.add(petItems.MonkeyPet(rarityUtil.Rarity.COMMON, 1));
        Pet.add(petItems.MonkeyPet(rarityUtil.Rarity.COMMON, 1));
        Pet.add(petItems.MonkeyPet(rarityUtil.Rarity.COMMON, 1));
    }

    public static void petState(Player p, String petType) {
        UUID pUUID = p.getUniqueId();

        if (PetData.containsKey(pUUID)) {
            String pet = getPet(p);
            if (petType.equalsIgnoreCase(pet)) {
                PetData.remove(pUUID);
            }
            if (!petType.equalsIgnoreCase("none")) {
                PetData.replace(pUUID, getPet(p), petType);
            } else {
                PetData.remove(pUUID);
            }
        }
    }

    public static String getPet(Player p) {
        return PetData.get(p.getUniqueId());
    }
}