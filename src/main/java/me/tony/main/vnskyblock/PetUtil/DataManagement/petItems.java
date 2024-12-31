package me.tony.main.vnskyblock.PetUtil.DataManagement;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.PDCUtil;
import me.tony.main.vnskyblock.Util.rarityUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class petItems {

    public static ItemStack MonkeyPet(rarityUtil.Rarity rarity, Integer level) {
        String monkeySkin = VNSkyblock.getInstance().getConfig().getString("monkey_skin");
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(rarity.getColor() + chatUtil.format("&lMonkey Pet &7[Lvl " + level + "]"));
        List<String> lore = new ArrayList<>();
        lore.add(chatUtil.format("&7Gives various bonuses when mining logs!"));
        lore.add(" ");
        lore.add(chatUtil.format("&2Pet Abilities"));
        lore.add(" ");
        lore.add(chatUtil.format("&eLogging Fortune"));
        lore.add(chatUtil.format("&7Grants a 25% chance to drop more logs."));
        if (rarity.name().equalsIgnoreCase("rare") || rarity.name().equalsIgnoreCase("legendary")) {
            lore.add(" ");
            lore.add(chatUtil.format("&eTIMBER!"));
            lore.add(chatUtil.format("&7Has a 50% chance to destroy 15 connected logs!"));
            lore.add(" ");
        }
        if (rarity.name().equalsIgnoreCase("legendary")) {
            lore.add(chatUtil.format("&eLumberjack Speed"));
            lore.add(chatUtil.format("&7Whenever you mine a log, it grants speed 2!"));
        }
        lore.add(" ");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PDCUtil.applySkin(item, monkeySkin);
        rarityUtil.setRarity(item, rarity);
        PDCUtil.addUUID(item);
        return item;
    }

    public static ItemStack RockPet(rarityUtil.Rarity rarity, Integer level) {
        String rockSkin = VNSkyblock.getInstance().getConfig().getString("rock_skin");
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(rarity.getColor() + chatUtil.format("&lRock Pet &7[Lvl " + level + "]"));
        List<String> lore = new ArrayList<>();
        lore.add(chatUtil.format("&7Grants various bonuses when mining"));
        lore.add(" ");
        lore.add(chatUtil.format("&2Pet Abilities"));
        lore.add(" ");
        lore.add(chatUtil.format("&eEfficient Miner"));
        lore.add(chatUtil.format("&7Adds an extra level to your Efficiency Enchantment"));
        lore.add(chatUtil.format("&a120 Second Cooldown"));
        if (rarity.name().equalsIgnoreCase("rare") || rarity.name().equalsIgnoreCase("legendary")) {
            lore.add(" ");
            lore.add(chatUtil.format("&eCrystal Rocks"));
            lore.add(chatUtil.format("&7Has a 5% chance to drop a valuable ore"));
        }
        if (rarity.name().equalsIgnoreCase("legendary")) {
            lore.add(" ");
            lore.add(chatUtil.format("&eRock Power!"));
            lore.add(chatUtil.format("&7Gain a 35% to mine all connected ores"));
        }
        lore.add(" ");
        meta.setLore(lore);
        item.setItemMeta(meta);
        PDCUtil.applySkin(item, rockSkin);
        rarityUtil.setRarity(item, rarity);
        PDCUtil.addUUID(item);
        return item;
    }
}
