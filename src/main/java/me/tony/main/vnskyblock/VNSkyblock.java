package me.tony.main.vnskyblock;

import me.tony.main.vnskyblock.MOTD.InitMOTD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VNSkyblock extends JavaPlugin {

    static VNSkyblock instance;
    static String version = "v1.0.0";

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        InitMOTD.setMOTD();


        System.out.println("VNSkyblock" + version + " Loaded Successfully, ");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static VNSkyblock getInstance() {
        return instance;
    }

}
