package me.tony.main.vnskyblock.PlayerCommands;

import me.tony.main.vnskyblock.PlayerCommands.DataFile.backpackData;
import me.tony.main.vnskyblock.PlayerCommands.Methods.backPackMethods;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class backpackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 0) {
                backPackMethods.playerBackpack(p);
            }
            if (args.length == 2) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("reset")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.format(target + " is not online!"));
                    } else if (target.isOnline()) {
                        backPackMethods.resetBackpack(target);
                        backpackData.saveBackPack(target);
                        p.sendMessage(ChatColor.format(target + " &7's backpack has been reset!"));
                    }
                }
            }
        }


        if (args.length == 0) {
            backPackMethods.playerBackpack(p);
        }

        return true;
    }
}
