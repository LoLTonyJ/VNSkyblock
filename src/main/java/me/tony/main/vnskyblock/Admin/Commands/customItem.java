package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.CustomItems.ItemStacks.infiniteWaterbucket;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.waterPumpBlock;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class customItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 1) {
                String item = args[0];
                if (item.equalsIgnoreCase("water") || item.equalsIgnoreCase("wb")) {
                    p.getInventory().addItem(infiniteWaterbucket.waterbucket());
                }
                if (item.equalsIgnoreCase("pump") || item.equalsIgnoreCase("waterpump")) {
                    p.getInventory().addItem(waterPumpBlock.waterPump());
                }
            }
        }

        return true;
    }
}
