package me.tony.main.vnskyblock.Util;

import org.bukkit.entity.Player;


public class direction {

    public static String getDirection(Player p) {
        return p.getLocation().getDirection().toString();
    }

    public static String getCardinalDirection(Player p) {
        double rotation = (p.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360;
        }

        if (0 <= rotation && rotation < 45) {
            return "WEST";
        } else if (45 <= rotation && rotation < 135) {
            return "NORTH";
        } else if (135 <= rotation && rotation < 225) {
            return "EAST";
        } else if (225 <= rotation && rotation < 315) {
            return "SOUTH";
        } else {
            return "WEST";
        }

    }

}
