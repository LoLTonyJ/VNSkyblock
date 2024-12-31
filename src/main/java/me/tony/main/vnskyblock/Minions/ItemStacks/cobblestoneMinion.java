package me.tony.main.vnskyblock.Minions.ItemStacks;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.rarityUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class cobblestoneMinion {

    public static ItemStack cobbleMin(Integer tier) {
        String url = VNSkyblock.getInstance().getConfig().getString("cobble_minion." + tier);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(chatUtil.format("&7Cobblestone Minion &7[Tier " + tier + "]"));
        List<String> lore = new ArrayList<>();
        lore.add(chatUtil.format("&7This minion will passively mine Cobblestone"));
        meta.setLore(lore);

        item.setItemMeta(meta);

        PDCUtil.applySkin(item, url);
        if (tier == 1) {
            rarityUtil.setRarity(item, rarityUtil.Rarity.COMMON);
        }
        if (tier == 2) {
            rarityUtil.setRarity(item, rarityUtil.Rarity.UNCOMMON);
        }
        if (tier == 3) {
            rarityUtil.setRarity(item, rarityUtil.Rarity.RARE);
        }

        PDCUtil.addUUID(item);
        PDCUtil.setItemID(item, "Cobblestone_Minion");

        return item;
    }


}
