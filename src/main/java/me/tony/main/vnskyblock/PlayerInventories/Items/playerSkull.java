package me.tony.main.vnskyblock.PlayerInventories.Items;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PlayerLevel.playerManager;
import me.tony.main.vnskyblock.PlayerTags.tagUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class playerSkull {

    public static ItemStack skull(Player player) {

        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();


        skullMeta.setDisplayName(ChatColor.format(player.getDisplayName()));
        skullMeta.setOwningPlayer(player);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.format("&bPlayer Level > &7[&b⭐" + playerManager.getLevel(player) + "&7]"));
        lore.add(ChatColor.format("&bPets Owned > " + playerOwnedPets.getOwnedPets(player).size()));
        lore.add(ChatColor.format("&bCosmetic Tags Owned > " + tagUtil.playerTotalOwnedTags(player)));
        skullMeta.setLore(lore);
        skullItem.setItemMeta(skullMeta);


        return skullItem;

    }


}
