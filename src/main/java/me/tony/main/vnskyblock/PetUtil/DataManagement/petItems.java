package me.tony.main.vnskyblock.PetUtil.DataManagement;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.tony.main.vnskyblock.PDC.Keys;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.rarityUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


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
        applySkin(item, monkeySkin);
        rarityUtil.setRarity(item, rarity);
        addUUID(item);
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
        applySkin(item, rockSkin);
        rarityUtil.setRarity(item, rarity);
        addUUID(item);
        return item;
    }

    public static String getItemUUID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.get(Keys.UNIQUE_IDENTIFIER, PersistentDataType.STRING);
    }

    public static ItemStack addUUID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        UUID uuid = UUID.randomUUID();
        container.set(Keys.UNIQUE_IDENTIFIER, PersistentDataType.STRING, uuid.toString());
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack applySkin(ItemStack item, String url) {

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

        if (url != null && !url.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            PlayerProfile playerProfile = Bukkit.createPlayerProfile(uuid, uuid.toString().substring(0, 16));
            PlayerTextures textures = playerProfile.getTextures();
            try {
                textures.setSkin(new URI(url).toURL());
            } catch (URISyntaxException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
            playerProfile.setTextures(textures);
            skullMeta.setOwnerProfile(playerProfile);
            item.setItemMeta(skullMeta);
            return item;
        }
        return item;
    }
}
