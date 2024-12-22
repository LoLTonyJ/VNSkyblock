package me.tony.main.vnskyblock;

import me.tony.main.vnskyblock.CurrencyUtil.currencyCommands;
import me.tony.main.vnskyblock.CurrencyUtil.currencyData;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.balCheck;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.IslandUtil.islandTeleport;
import me.tony.main.vnskyblock.MOTD.initMOTD;
import me.tony.main.vnskyblock.PetUtil.Commands.petAdminCommands;
import me.tony.main.vnskyblock.PetUtil.Commands.petMainCommand;
import me.tony.main.vnskyblock.PetUtil.Inventories.petDisplayListener;
import me.tony.main.vnskyblock.PetUtil.Listeners.joinListener;
import me.tony.main.vnskyblock.PetUtil.Listeners.petAddListener;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerData;
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

        try {
            playerData.Load();
            currencyData.Load();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        currencyData.loadCurrency();

        getServer().getPluginManager().registerEvents(new initMOTD(), this);
        getServer().getPluginManager().registerEvents(new gemConomy(), this);
        getServer().getPluginManager().registerEvents(new islandTeleport(), this);
        getServer().getPluginManager().registerEvents(new joinListener(), this);
        getServer().getPluginManager().registerEvents(new petDisplayListener(), this);
        getServer().getPluginManager().registerEvents(new petAddListener(), this);

        //Currency
        getCommand("gem").setExecutor(new currencyCommands());
        getCommand("bal").setExecutor(new balCheck());

        //Pet Shit
        getCommand("petadmin").setExecutor(new petAdminCommands());
        getCommand("pet").setExecutor(new petMainCommand());


        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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
