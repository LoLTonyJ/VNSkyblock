package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.loreCheck;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets.getActivePet;

public class petBonus {

    public static String getBonus(Player p) {

        ItemStack item = getActivePet(p);
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        String displayName = meta.getDisplayName();
        String strippedName = displayName.strip();

        if (strippedName.toLowerCase().contains("rock") && loreCheck.containsWord(meta.getLore(), "COMMON")) {
            return "MINING, COMMON";
        }
        if (strippedName.toLowerCase().contains("rock") && loreCheck.containsWord(meta.getLore(), "RARE")) {
            return "MINING, RARE";
        }
        if (strippedName.toLowerCase().contains("rock") && loreCheck.containsWord(meta.getLore(), "LEGENDARY")) {
            return "MINING, LEGENDARY";
        }
        if (strippedName.toLowerCase().contains("monkey") && loreCheck.containsWord(meta.getLore(), "COMMON")) {
            return "LOGGING, COMMON";
        }
        if (strippedName.toLowerCase().contains("monkey") && loreCheck.containsWord(meta.getLore(), "RARE")) {
            return "LOGGING, RARE";
        }
        if (strippedName.toLowerCase().contains("monkey") && loreCheck.containsWord(meta.getLore(), "LEGENDARY")) {
            return "LOGGING, LEGENDARY";
        }

        return null;
    }

}
