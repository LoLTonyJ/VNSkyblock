package me.tony.main.vnskyblock;

import me.tony.main.vnskyblock.CurrencyUtil.currencyCommands;
import me.tony.main.vnskyblock.CurrencyUtil.currencyData;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.balCheck;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.IslandUtil.islandTeleport;
import me.tony.main.vnskyblock.MOTD.initMOTD;
import me.tony.main.vnskyblock.NPC.npcClick;
import me.tony.main.vnskyblock.PetUtil.Commands.petAdminCommands;
import me.tony.main.vnskyblock.PetUtil.Commands.petMainCommand;
import me.tony.main.vnskyblock.PetUtil.Listeners.petDisplayListener;
import me.tony.main.vnskyblock.PetUtil.Listeners.*;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerData;
import me.tony.main.vnskyblock.PlayerLevel.chatFormat;
import me.tony.main.vnskyblock.PlayerLevel.levelCommands;
import me.tony.main.vnskyblock.PlayerLevel.playerFile;
import me.tony.main.vnskyblock.PlayerTags.tagCommand;
import me.tony.main.vnskyblock.PlayerTags.tagFile;
import me.tony.main.vnskyblock.Scoreboard.initScoreboard;
import me.tony.main.vnskyblock.Scoreboard.scoreboardUtil;
import me.tony.main.vnskyblock.Tablist.initTablist;
import me.tony.main.vnskyblock.Tablist.tablistUtil;
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
            playerFile.Load();
            tagFile.Load();
            playerData.Load();
            currencyData.Load();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        playerFile.loadPlayerLevels();
        playerData.loadPetList();
        currencyData.loadCurrency();
        tagFile.loadOwnedTags();

        getServer().getPluginManager().registerEvents(new initMOTD(), this);
        getServer().getPluginManager().registerEvents(new gemConomy(), this);
        getServer().getPluginManager().registerEvents(new islandTeleport(), this);
        getServer().getPluginManager().registerEvents(new initScoreboard(), this);
        getServer().getPluginManager().registerEvents(new initTablist(), this);
        getServer().getPluginManager().registerEvents(new chatFormat(), this);

        // Pet Listeners
        getServer().getPluginManager().registerEvents(new joinListener(), this);
        getServer().getPluginManager().registerEvents(new petDisplayListener(), this);
        getServer().getPluginManager().registerEvents(new petAddListener(), this);
        getServer().getPluginManager().registerEvents(new relocatePet(), this);
        getServer().getPluginManager().registerEvents(new breakBlockPetBonus(), this);
        getServer().getPluginManager().registerEvents(new enchantmentPetBonus(), this);
        getServer().getPluginManager().registerEvents(new preventDupe(), this);

        // NPC
        getServer().getPluginManager().registerEvents(new npcClick(), this);

        // Tags
        getCommand("tag").setExecutor(new tagCommand());

        //Currency
        getCommand("gem").setExecutor(new currencyCommands());
        getCommand("bal").setExecutor(new balCheck());

        //Pet Shit
        getCommand("petadmin").setExecutor(new petAdminCommands());
        getCommand("pet").setExecutor(new petMainCommand());

        // Levels
        getCommand("leveladmin").setExecutor(new levelCommands());


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
        currencyData.saveCurrency();
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
