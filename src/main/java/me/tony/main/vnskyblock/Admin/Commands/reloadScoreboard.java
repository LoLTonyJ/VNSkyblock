package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Scoreboard.scoreboardUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class reloadScoreboard implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

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
                        p.sendMessage(ChatColor.format("&aReloaded Plugin Config"));
                    }
                }
            }
        }

        return true;
    }
}
