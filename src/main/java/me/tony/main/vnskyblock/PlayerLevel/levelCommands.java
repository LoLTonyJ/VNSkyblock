package me.tony.main.vnskyblock.PlayerLevel;

import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class levelCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 0) {
                p.sendMessage(chatUtil.format("&bUsage: /la add/set <player> <num>"));
            }
            if (args.length == 2) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("get")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    p.sendMessage(chatUtil.format(target.getDisplayName() + " &cLevel is &a" + playerManager.getLevel(target)));
                }
                if (subCommand.equalsIgnoreCase("reset")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    playerManager.resetLevel(target);
                }
            }
            if (args.length == 3) {
                // la set/add <player> <num>
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    int num = Integer.parseInt(args[2]);
                    playerManager.setLevel(target, num);
                }
                if (subCommand.equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    int num = Integer.parseInt(args[2]);
                    playerManager.addLevel(target, num);
                }
            }
        }


        return true;
    }
}
