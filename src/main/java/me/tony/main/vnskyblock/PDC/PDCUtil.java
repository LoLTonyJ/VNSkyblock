package me.tony.main.vnskyblock.PDC;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class PDCUtil {

    public static String getItemUUID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.get(Keys.UNIQUE_IDENTIFIER, PersistentDataType.STRING);
    }

    public static boolean itemKeyValue(ItemStack item, NamespacedKey key, String keyEquals) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.get(key, PersistentDataType.STRING) == null) return false;
        return container.get(key, PersistentDataType.STRING).equalsIgnoreCase(keyEquals);
    }

    public static boolean itemContainsKey(NamespacedKey key, ItemStack item) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(key);
    }

    public static boolean entityKeyValue(Entity ent, NamespacedKey key, String keyEquals) {
        PersistentDataContainer container = ent.getPersistentDataContainer();
        if (container.get(key, PersistentDataType.STRING) == null) return false;
        return container.get(key, PersistentDataType.STRING).equals(keyEquals);
    }


    public static boolean entityContainsKey(NamespacedKey key, Entity ent) {
        PersistentDataContainer container = ent.getPersistentDataContainer();
        return container.has(key);
    }

    public static ItemStack addUUID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        UUID uuid = UUID.randomUUID();
        container.set(Keys.UNIQUE_IDENTIFIER, PersistentDataType.STRING, uuid.toString());
        item.setItemMeta(meta);
        return item;
    }

    public static Entity setEntityID(Entity ent, String id) {
        PersistentDataContainer container = ent.getPersistentDataContainer();
        container.set(Keys.ENTITY_ID, PersistentDataType.STRING, id);
        return ent;
    }

    public static ItemStack setItemID(ItemStack item, String id) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(Keys.ITEM_ID, PersistentDataType.STRING, id);
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
