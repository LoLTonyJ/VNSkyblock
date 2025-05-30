package me.tony.main.vnskyblock;

import me.tony.main.vnskyblock.Admin.Commands.customItem;
import me.tony.main.vnskyblock.Admin.Commands.punishCommands;
import me.tony.main.vnskyblock.Admin.Commands.reloadCommand;
import me.tony.main.vnskyblock.Admin.FileManipulation.bannedPlayers;
import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Admin.GUI.Events.ClickInventory;
import me.tony.main.vnskyblock.Admin.GUI.Events.PunishListenerEvents;
import me.tony.main.vnskyblock.Admin.PlayerManager.FreezePlayer;
import me.tony.main.vnskyblock.Admin.PlayerManager.PunishManager;
import me.tony.main.vnskyblock.CurrencyUtil.currencyCommands;
import me.tony.main.vnskyblock.CurrencyUtil.currencyData;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.balCheck;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.CustomItems.Events.DirtWand;
import me.tony.main.vnskyblock.CustomItems.Events.TeleportStick;
import me.tony.main.vnskyblock.CustomItems.Events.WaterPump;
import me.tony.main.vnskyblock.CustomItems.Events.Waterbucket;
import me.tony.main.vnskyblock.CustomMobs.Events.Healthbars;
import me.tony.main.vnskyblock.IslandUtil.Events.BiscuitInteract;
import me.tony.main.vnskyblock.IslandUtil.islandTeleport;
import me.tony.main.vnskyblock.MOTD.initMOTD;
import me.tony.main.vnskyblock.Minions.Commands.AdminCommands;
import me.tony.main.vnskyblock.Minions.DataFile.FileManager;
import me.tony.main.vnskyblock.Minions.Listener.onInteract;
import me.tony.main.vnskyblock.Minions.Listener.onPlace;
import me.tony.main.vnskyblock.NPC.Inventories.BankData.FileManipulation;
import me.tony.main.vnskyblock.NPC.Inventories.BankData.playerLoadBankData;
import me.tony.main.vnskyblock.NPC.Inventories.Events.bankEvents;
import me.tony.main.vnskyblock.NPC.Inventories.Events.itemPurchase;
import me.tony.main.vnskyblock.NPC.npcClick;
import me.tony.main.vnskyblock.PetUtil.ArmorStandUtil.displayPetHead;
import me.tony.main.vnskyblock.PetUtil.Commands.petAdminCommands;
import me.tony.main.vnskyblock.PetUtil.Commands.petMainCommand;
import me.tony.main.vnskyblock.PetUtil.Listeners.petDisplayListener;
import me.tony.main.vnskyblock.PetUtil.Listeners.*;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerData;
import me.tony.main.vnskyblock.PlayerCommands.DataFile.backpackData;
import me.tony.main.vnskyblock.PlayerCommands.Events.backPackEvent;
import me.tony.main.vnskyblock.PlayerCommands.backpackCommand;
import me.tony.main.vnskyblock.PlayerCommands.deleteIsland;
import me.tony.main.vnskyblock.PlayerCommands.vault;
import me.tony.main.vnskyblock.PlayerInventories.Inventories.clickedPlayerInventory;
import me.tony.main.vnskyblock.PlayerInventories.Listeners.*;
import me.tony.main.vnskyblock.PlayerLevel.chatFormat;
import me.tony.main.vnskyblock.PlayerLevel.levelCommands;
import me.tony.main.vnskyblock.PlayerLevel.playerFile;
import me.tony.main.vnskyblock.Scoreboard.initScoreboard;
import me.tony.main.vnskyblock.Scoreboard.scoreboardUtil;
import me.tony.main.vnskyblock.Tablist.initTablist;
import me.tony.main.vnskyblock.Tablist.tablistUtil;
import me.tony.main.vnskyblock.Util.antiVoid;
import me.tony.main.vnskyblock.Util.inventoryNoClick;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class VNSkyblock extends JavaPlugin {

    static VNSkyblock instance;
    static String version = "v1.0.0";
    private static Economy econ = null;


    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        tablistUtil.startTab();


        try {
            FileManager.Load();
            backpackData.Load();
            playerFile.Load();
            playerData.Load();
            currencyData.Load();
            FileManipulation.Load();
            bannedPlayers.Load();
            punishConfiguration.Load();
            PunishManager.Load();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        playerFile.loadPlayerLevels();
        playerData.loadPetList();
        currencyData.loadCurrency();
        backpackData.loadPlayerBackpacks();
   //     FileManager.loadMinionList();
   //     FileManager.loadMinionStorage();
        playerData.loadPetExperience();
        // Bank Data Load
        getServer().getPluginManager().registerEvents(new playerLoadBankData(), this);

        getServer().getPluginManager().registerEvents(new BiscuitInteract(), this);
        getServer().getPluginManager().registerEvents(new initMOTD(), this);
        getServer().getPluginManager().registerEvents(new gemConomy(), this);
        getServer().getPluginManager().registerEvents(new islandTeleport(), this);
        getServer().getPluginManager().registerEvents(new initScoreboard(), this);
        getServer().getPluginManager().registerEvents(new initTablist(), this);
        getServer().getPluginManager().registerEvents(new antiVoid(), this);
        getServer().getPluginManager().registerEvents(new itemPurchase(), this);
        getServer().getPluginManager().registerEvents(new clickedPlayerInventory(), this);
        getServer().getPluginManager().registerEvents(new inventoryNoClick(), this);
        //getServer().getPluginManager().registerEvents(new viewLoadout(), this);
        getServer().getPluginManager().registerEvents(new bankEvents(), this);

        // Administration
        getServer().getPluginManager().registerEvents(new FreezePlayer(), this);
        getServer().getPluginManager().registerEvents(new ClickInventory(), this);
        getServer().getPluginManager().registerEvents(new PunishListenerEvents(), this);
        getCommand("punish").setExecutor(new punishCommands());


        // Player Inventory stuffz
        getServer().getPluginManager().registerEvents(new preventDrop(), this);
        getServer().getPluginManager().registerEvents(new preventMove(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onClick(), this);
        getServer().getPluginManager().registerEvents(new inventoryClick(), this);

        // Pet Listeners
        getServer().getPluginManager().registerEvents(new joinListener(), this);
        getServer().getPluginManager().registerEvents(new petDisplayListener(), this);
        getServer().getPluginManager().registerEvents(new petAddListener(), this);
        getServer().getPluginManager().registerEvents(new relocatePet(), this);
        getServer().getPluginManager().registerEvents(new breakBlockPetBonus(), this);
        getServer().getPluginManager().registerEvents(new enchantmentPetBonus(), this);
        getServer().getPluginManager().registerEvents(new preventDupe(), this);

        // Island Management
        getCommand("isdelete").setExecutor(new deleteIsland());

        // Minions
        getServer().getPluginManager().registerEvents(new onPlace(), this);
        getServer().getPluginManager().registerEvents(new onInteract(), this);

        getCommand("minion").setExecutor(new AdminCommands());

        // Custom Items
        getServer().getPluginManager().registerEvents(new Waterbucket(), this);
        getServer().getPluginManager().registerEvents(new WaterPump(), this);
        getServer().getPluginManager().registerEvents(new DirtWand(), this);
        getServer().getPluginManager().registerEvents(new TeleportStick(), this);

        getCommand("customitem").setExecutor(new customItem());

        // Vault
        getCommand("vault").setExecutor(new vault());

        // Entity
        getServer().getPluginManager().registerEvents(new Healthbars(), this);

        // Backpacks
        getServer().getPluginManager().registerEvents(new backPackEvent(), this);

        getCommand("backpack").setExecutor(new backpackCommand());

        // NPC
        getServer().getPluginManager().registerEvents(new npcClick(), this);


        //Currency
        getCommand("gem").setExecutor(new currencyCommands());
        getCommand("bal").setExecutor(new balCheck());

        //Pet Shit
        getCommand("petadmin").setExecutor(new petAdminCommands());
        getCommand("pet").setExecutor(new petMainCommand());

        // Levels
        getCommand("leveladmin").setExecutor(new levelCommands());
        getServer().getPluginManager().registerEvents(new chatFormat(), this);

        // Admin General Commands
        getCommand("voltadmincontrol").setExecutor(new reloadCommand());


        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        scoreboardUtil.reloadScoreboard();
        System.out.println("\n VNSkyblock " + version + " Loaded Successfully \n");
    }

    @Override
    public void onDisable() {
        displayPetHead.pluginReloadArmorStandRemove();
        currencyData.saveCurrency();
       // punishConfiguration.Save();
        PunishManager.Save();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static VNSkyblock getInstance() {
        return instance;
    }

}
