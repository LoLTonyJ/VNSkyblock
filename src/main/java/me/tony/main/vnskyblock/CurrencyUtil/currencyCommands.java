package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.Util.chatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class currencyCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (!permCheck.isAdmin(p)) {
            p.sendMessage(chatUtil.format("&cYou do not have permission to use this command!"));
        }

        // /gem remove/add/set player amount
        if (args.length == 0) {
            p.sendMessage(chatUtil.format("&bUsage: /gem <remove/add/reset/set> <player> <amount>"));
        }
        if (args.length == 2) {
            String subCommand = args[0];
            Player target = Bukkit.getPlayer(args[1]);
            if (subCommand.equalsIgnoreCase("reset")) {
                if (target != null) {
                    gemConomy.resetBalance(p, target);
                }
            }
        }
        if (args.length == 3) {
            String subCommand = args[0];
            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);
            if (target == null) return true;

            if (subCommand.equalsIgnoreCase("remove")) {
                gemConomy.removeBalance(p, target, amount);
                currencyData.saveCurrency();
            }
            if (subCommand.equalsIgnoreCase("add")) {
                gemConomy.addBalance(p, target, amount);
                currencyData.saveCurrency();
            }
            if (subCommand.equalsIgnoreCase("set")) {
                gemConomy.setBalance(p, target, amount);
                currencyData.saveCurrency();
            }
        }
        return true;
    }
}
