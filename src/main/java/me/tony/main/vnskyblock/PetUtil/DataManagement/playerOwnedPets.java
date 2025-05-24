package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Scoreboard.scoreboardUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.PDC.PDCUtil;
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
    public static HashMap<UUID, Integer> petExperience = new HashMap<>();

    public static void initPetList(Player p) {
        if (PlayerPets.containsKey(p.getUniqueId())) return;
        List<ItemStack> newList = new ArrayList<>();
        newList.add(petItems.RockPet(rarityUtil.Rarity.COMMON, 1));
        PlayerPets.put(p.getUniqueId(), newList);
        playerData.savePetList(p, newList);

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
        p.sendMessage(ChatColor.format("&7You have de-activated " + getActivePet(p).getItemMeta().getDisplayName()));
        activePet.remove(p.getUniqueId());
        scoreboardUtil.reloadPlayerScoreboard(p);
    }

    public static void activatePet(Player p, ItemStack itemStack) {

        if (itemStack == null) return;

        if (!activePet.containsKey(p.getUniqueId())) {
            activePet.put(p.getUniqueId(), itemStack);
            p.sendMessage(ChatColor.format("&7You have set " + itemStack.getItemMeta().getDisplayName() + " &7as your active pet!"));
            scoreboardUtil.reloadPlayerScoreboard(p);
            return;
        }
        if (activePet.containsKey(p.getUniqueId())) {
            ItemStack oldPet = activePet.get(p.getUniqueId());
            activePet.replace(p.getUniqueId(), oldPet, itemStack);
            p.sendMessage(ChatColor.format("&7You have set " + itemStack.getItemMeta().getDisplayName() + " &7as your active pet!"));
            scoreboardUtil.reloadPlayerScoreboard(p);
        }
    }

    public static List<ItemStack> getOwnedPets(Player p) {
        return PlayerPets.get(p.getUniqueId());
    }

    public static ItemStack getActivePet(Player p) {
        if (!activePet.containsKey(p.getUniqueId())) return null;
        return activePet.get(p.getUniqueId());
    }

    public static void updatePetExperience(Player p, ItemStack item, Integer value) {
        if (petExperience.containsKey(UUID.fromString(PDCUtil.getItemUUID(item)))) {
            UUID uuid = UUID.fromString(PDCUtil.getItemUUID(item));
            int experience = petExperience.get(uuid);
            if (experience >= 500) {
                editPetLevel(p);
                petExperience.put(uuid, 0);
                playerData.savePetExperience(uuid, petExperience.get(uuid));
            }
            petExperience.replace(uuid, petExperience.get(uuid), petExperience.get(uuid) + value);
            playerData.savePetExperience(uuid, petExperience.get(uuid));
            scoreboardUtil.reloadPlayerScoreboard(p);
        } else {
            petExperience.put(UUID.fromString(PDCUtil.getItemUUID(item)), value);
            playerData.savePetExperience(UUID.fromString(PDCUtil.getItemUUID(item)), petExperience.get(UUID.fromString(PDCUtil.getItemUUID(item))));
        }
    }

    public static int getCurrentExperience(ItemStack item) {
        if (item == null) return 0;
        return petExperience.getOrDefault(UUID.fromString(PDCUtil.getItemUUID(item)), 0);
    }

    public static String getActivePetName(Player p) {
        ItemStack item = getActivePet(p);
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        return ChatColor.format(meta.getDisplayName());
    }

    public static ItemStack setPetLevel(Player p, ItemStack item, int level) {
        if (item == null || !item.hasItemMeta()) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return item;
        String displayName = meta.getDisplayName();
        String newDisplayName = displayName.replaceAll("\\[Lvl \\d+]", ChatColor.format("[Lvl " + level + "]"));
        meta.setDisplayName(newDisplayName);
        item.setItemMeta(meta);
        p.sendMessage(ChatColor.format("&7You've set the level to " + level + "!"));
        return item;
    }

    public static ItemStack editPetLevel(Player p) {
        ItemStack item = getActivePet(p);
        ItemMeta meta = item.getItemMeta();
        List<ItemStack> playerPets = getOwnedPets(p);
        int index = playerPets.indexOf(item);
        int level = getPetLevel(p, item) + 1;
        String displayName = meta.getDisplayName();
        String newDisplayName = displayName.replaceAll("\\[Lvl \\d+]", ChatColor.format("[Lvl " + level + "]"));

        meta.setDisplayName(newDisplayName);
        item.setItemMeta(meta);
        playerPets.set(index, item);
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
