package me.tony.main.vnskyblock;

import me.tony.main.vnskyblock.CurrencyUtil.CurrencyCommands;
import me.tony.main.vnskyblock.CurrencyUtil.CurrencyData;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.BalCheck;
import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.GemConomy;
import me.tony.main.vnskyblock.MOTD.InitMOTD;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
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
            CurrencyData.Load();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        CurrencyData.loadCurrency();

        getServer().getPluginManager().registerEvents(new InitMOTD(), this);
        getServer().getPluginManager().registerEvents(new GemConomy(), this);

        //Currency
        getCommand("gem").setExecutor(new CurrencyCommands());
        getCommand("bal").setExecutor(new BalCheck());




















        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println("\n VNSkyblock " + version + " Loaded Successfully \n");
    }

    @Override
    public void onDisable() {
        CurrencyData.saveCurrency();
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
