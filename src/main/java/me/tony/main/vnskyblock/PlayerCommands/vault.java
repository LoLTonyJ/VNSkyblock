package me.tony.main.vnskyblock.PlayerCommands;

import me.tony.main.vnskyblock.PlayerCommands.Methods.vaultMethods;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class vault implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 2) {
                //vault view/reset <player>
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("view")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.format("&cPlayer not online!"));
                    } else {
                            vaultMethods.playerVault(target);

                    }
                }
                if (subCommand.equalsIgnoreCase("reset")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.format("&cPlayer not online!"));
                    } else {
                        if (vaultMethods.getPlayerVault(target) != null) {
                            vaultMethods.removePlayerVault(target);
                        } else {
                            p.sendMessage(ChatColor.format("&cNo Vault found!"));
                        }
                    }
                }
            }
        }

        if (args.length == 0) {
            vaultMethods.playerVault(p);
        }

        return true;
    }
}
