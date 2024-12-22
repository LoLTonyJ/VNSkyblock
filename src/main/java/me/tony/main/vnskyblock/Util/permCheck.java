package me.tony.main.vnskyblock.Util;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;

public class permCheck {

    public static boolean isAdmin(Player p) {
        String perm = VNSkyblock.getInstance().getConfig().getString("administration_permission");
        return p.hasPermission(perm);
    }

    public static boolean isMod(Player p) {
        String perm = VNSkyblock.getInstance().getConfig().getString("moderation_permission");
        return p.hasPermission(perm);
    }

    public static boolean isHelper(Player p) {
        String perm = VNSkyblock.getInstance().getConfig().getString("helper_permission");
        return p.hasPermission(perm);
    }

}
