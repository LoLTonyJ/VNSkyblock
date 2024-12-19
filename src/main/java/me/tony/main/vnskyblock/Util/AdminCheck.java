package me.tony.main.vnskyblock.Util;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;

public class AdminCheck {


    public static boolean isAdmin(Player p) {
        String perm = VNSkyblock.getInstance().getConfig().getString("administration_permission");
        return p.hasPermission(perm);
    }


}
