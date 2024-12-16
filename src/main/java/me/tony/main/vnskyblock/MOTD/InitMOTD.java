package me.tony.main.vnskyblock.MOTD;

import me.tony.main.vnskyblock.Util.ChatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;

public class InitMOTD {

    public static void setMOTD() {
        String motd = VNSkyblock.getInstance().getConfig().getString("motd");
        Bukkit.getServer().setMotd(ChatUtil.format(motd));
    }


}
