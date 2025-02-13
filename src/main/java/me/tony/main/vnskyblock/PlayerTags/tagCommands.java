package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class tagCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.format("&bUsage: /tag <set/remove/add> <player> <tag>"));
            }
            if (args.length == 3) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.format(target + " &7is not online!"));
                        return true;
                    }

                }

            }
        }

        return true;
    }
}
