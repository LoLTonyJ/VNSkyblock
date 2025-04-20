package me.tony.main.vnskyblock.PlayerCommands.Methods;

import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PlayerInventories.Items.petStorage;
import me.tony.main.vnskyblock.PlayerInventories.Items.playerSkull;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.inventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class viewLoadout implements Listener {


    public static HashMap<Player, Player> viewList = new HashMap<>();

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {

        Player p = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        clickedPlayerLoadout(p, target);

    }

    public static ItemStack vaultItemPlaceholder() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bPlayer Vault"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("&7Left-Click to view Player's Vault!");
        lore.add(" ");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack islandLevelPlaceholder(Player player) {
        ItemStack item = new ItemStack(Material.OAK_SAPLING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.format("&bPlayer Island Level"));
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.format("&cPlayers Island Standing."));
        lore.add(" ");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }


    public static Inventory clickedPlayerLoadout(Player p, Player target) {

        Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.format(target.getDisplayName() + " &7Loadout"));
        ItemStack activePet = playerOwnedPets.getActivePet(target);
        ItemStack playerHelmet = target.getEquipment().getHelmet();
        ItemStack playerChestplate = target.getEquipment().getChestplate();
        ItemStack playerLeggings = target.getEquipment().getLeggings();
        ItemStack playerBoots = target.getEquipment().getBoots();
        ItemStack holdingItem = target.getEquipment().getItemInMainHand();

        inv.setItem(2, holdingItem);
        inv.setItem(10, activePet);
        inv.setItem(11, playerHelmet);
        inv.setItem(14, playerSkull.skull(target));
        inv.setItem(20, playerChestplate);
        inv.setItem(23, vaultItemPlaceholder());
        inv.setItem(24, islandLevelPlaceholder(target));
        inv.setItem(29, playerLeggings);
        inv.setItem(32, new ItemStack(Material.DIAMOND_PICKAXE));
        inv.setItem(38, playerBoots);
        inv.setItem(41, petStorage.petItem());

        inventoryUtil.FillInventory(Material.BLACK_STAINED_GLASS_PANE, inv);

        p.openInventory(inv);
        viewList.put(p, target);

        return inv;
    }


}
