package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class playerOwnedPets {

    public static HashMap<UUID, List<ItemStack>> PlayerPets = new HashMap<>();
    private static HashMap<UUID, ItemStack> activePet = new HashMap<>();

    public static void initPetList(Player p) {
        if (PlayerPets.containsKey(p.getUniqueId())) return;
        List<ItemStack> newList = new ArrayList<>();
        newList.add(petItems.RockPet(rarityUtil.Rarity.COMMON, 1));
        PlayerPets.put(p.getUniqueId(), newList);

    }

    public static void setList(UUID pUUID, List<ItemStack> list) {
        PlayerPets.put(pUUID, list);
    }

    public static void addPet(Player p, ItemStack item) {
        List<ItemStack> pets = PlayerPets.computeIfAbsent(p.getUniqueId(), k -> new ArrayList<>());
        pets.add(item);
        playerData.savePetList(p, PlayerPets.get(p.getUniqueId()));
    }

    public static void removePet(Player p, ItemStack item) {
        List<ItemStack> pets = PlayerPets.get(p.getUniqueId());
        if (pets.contains(item)) {
            pets.remove(item);
            p.getInventory().addItem(item);
            playerData.savePetList(p, PlayerPets.get(p.getUniqueId()));
        }
    }

    public static void removeActivePet(Player p) {
        activePet.remove(p.getUniqueId());
    }

    public static void activatePet(Player p, ItemStack itemStack) {

        if (itemStack == null) return;

        if (!activePet.containsKey(p.getUniqueId())) {
            activePet.put(p.getUniqueId(), itemStack);
            p.sendMessage(chatUtil.format("&7You have set " + itemStack.getItemMeta().getDisplayName() + " &7as your active pet!"));
            return;
        }
        if (activePet.containsKey(p.getUniqueId())) {
            ItemStack oldPet = activePet.get(p.getUniqueId());
            activePet.replace(p.getUniqueId(), oldPet, itemStack);
            p.sendMessage(chatUtil.format("&7You have set " + itemStack.getItemMeta().getDisplayName() + " &7as your active pet!"));
        }
    }

    public static List<ItemStack> getOwnedPets(Player p) {
        return PlayerPets.get(p.getUniqueId());
    }

    public static ItemStack getActivePet(Player p) {
        if (!activePet.containsKey(p.getUniqueId())) return null;
        return activePet.get(p.getUniqueId());
    }

    public static ItemStack setPetLevel(Player p, ItemStack item, int level) {
        if (item == null || !item.hasItemMeta()) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return item;
        String displayName = meta.getDisplayName();
        String newDisplayName = displayName.replaceAll("\\[Lvl \\d+]", chatUtil.format("[Lvl " + level + "]"));
        meta.setDisplayName(newDisplayName);
        item.setItemMeta(meta);
        p.sendMessage(chatUtil.format("&7You've set the level to " + level + "!"));
        return item;
    }

    public static ItemStack editPetLevel(Player p) {
        ItemStack item = getActivePet(p);
        ItemMeta meta = item.getItemMeta();
        int level = getPetLevel(p, item) + 1;
        String displayName = meta.getDisplayName();
        String newDisplayName = displayName.replaceAll("\\[Lvl \\d+]", chatUtil.format("[Lvl " + level + "]"));

        meta.setDisplayName(newDisplayName);
        item.setItemMeta(meta);
        return item;
    }

    public static Integer getPetLevel(Player p, ItemStack itemStack) {
        if (getActivePet(p) == null) return 0;
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return 0;
        String displayName = meta.getDisplayName();
        Pattern pattern = Pattern.compile("\\[Lvl (\\d+)]");
        Matcher matcher = pattern.matcher(displayName);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }


}
