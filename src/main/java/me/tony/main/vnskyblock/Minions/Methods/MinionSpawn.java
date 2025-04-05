package me.tony.main.vnskyblock.Minions.Methods;

import me.tony.main.vnskyblock.PDC.PDCUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.itemMetaEditor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MinionSpawn {


    public static ArmorStand createCobbleMinion(Location loc, World w, ItemStack itemStack) {


        ArmorStand armorStand = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return armorStand;
        String name = meta.getDisplayName();

        armorStand.setSmall(true);
        armorStand.setCustomName((ChatColor.format(name)));
        armorStand.getEquipment().setHelmet(itemStack);
        armorStand.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
        armorStand.getEquipment().setChestplate(itemMetaEditor.colorChange(new ItemStack(Material.LEATHER_CHESTPLATE), Color.TEAL));
        armorStand.getEquipment().setLeggings(itemMetaEditor.colorChange(new ItemStack(Material.LEATHER_LEGGINGS), Color.TEAL));
        armorStand.getEquipment().setBoots(itemMetaEditor.colorChange(new ItemStack(Material.LEATHER_BOOTS), Color.TEAL));
        armorStand.setMarker(false);
        armorStand.setArms(true);
        armorStand.setGravity(false);
        armorStand.setCanPickupItems(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomNameVisible(true);

        PDCUtil.setEntityID(armorStand, "Minion");

        return armorStand;
    }


}
