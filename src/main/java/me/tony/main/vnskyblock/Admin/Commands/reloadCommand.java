package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Scoreboard.scoreboardUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class reloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
           if (args.length == 1) {
               String command = args[0];
               if (command.equalsIgnoreCase("reload")) {
                   VNSkyblock.getInstance().reloadConfig();
                   try {
                       punishConfiguration.reloadPunishConfig();
                   } catch (IOException | InvalidConfigurationException e) {
                       throw new RuntimeException(e);
                   }
                   System.out.println("Reloaded Both Punish, and Config.yml files.");
               }
           }
        }

        Player p = (Player) sender;
        if (permCheck.isAdmin(p)) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.format("&bUsage: /vac reload scoreboard/config"));
            }
            if (args.length == 2) {
                String subCommand = args[0];
                String module = args[1];
                if (subCommand.equalsIgnoreCase("reload")) {
                    if (module.equalsIgnoreCase("scoreboard")) {
                        scoreboardUtil.reloadScoreboard();
                        p.sendMessage(ChatColor.format("&aReloaded all online players Scoreboards!"));
                    }
                    if (module.equalsIgnoreCase("config")) {
                        VNSkyblock.getInstance().reloadConfig();
                        p.sendMessage(ChatColor.format("&aReloaded Plugin config.yml"));
                    }
                    if (module.equalsIgnoreCase("punish")) {
                        try {
                            punishConfiguration.reloadPunishConfig();
                            p.sendMessage(ChatColor.format("&aReloaded punishconfig.yml"));
                        } catch (IOException | InvalidConfigurationException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        return true;
    }
}
