package me.tony.main.vnskyblock.Util;

import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.entity.Player;

import java.util.Objects;

public class permCheck {

    public static boolean isAdmin(Player p) {
        return p.hasPermission(Objects.requireNonNull(VNSkyblock.getInstance().getConfig().getString("administration_permission")));
    }

    public static boolean isMod(Player p) {
        return p.hasPermission(Objects.requireNonNull(VNSkyblock.getInstance().getConfig().getString("moderation_permission")));
    }

    public static boolean isHelper(Player p) {
        return p.hasPermission(Objects.requireNonNull(VNSkyblock.getInstance().getConfig().getString("helper_permission")));
    }

}
