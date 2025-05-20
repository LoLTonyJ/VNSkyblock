package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class punishCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (permCheck.isAdmin(p)) {
            if (args.length == 3) {
                // /punish player <ban> <reason>
                Player target = Bukkit.getPlayer(args[0]);
                String subCommand = args[1];
                String reason = args[2];
                if (subCommand.equalsIgnoreCase("ban")) {
                    System.out.println(punishConfiguration.getReasonBuilder(reason));
                }
            }
        }
        return true;
    }
}
