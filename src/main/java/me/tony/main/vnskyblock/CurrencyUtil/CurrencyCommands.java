package me.tony.main.vnskyblock.CurrencyUtil;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.GemConomy;
import me.tony.main.vnskyblock.Util.AdminCheck;
import me.tony.main.vnskyblock.Util.ChatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CurrencyCommands implements CommandExecutor {

    private static String adminPerm = VNSkyblock.getInstance().getConfig().getString("administration_permission");


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (!AdminCheck.isAdmin(p)) return true;

        // /gem remove/add/set player amount
        if (args.length == 0) {
            p.sendMessage(ChatUtil.format("&bUsage: /gem <remove/add/reset/set> <player> <amount>"));
        }
        if (args.length == 2) {
            String subCommand = args[0];
            Player target = Bukkit.getPlayer(args[1]);
            if (subCommand.equalsIgnoreCase("reset")) {
                if (target != null) {
                    GemConomy.resetBalance(p, target);
                }
            }
        }
        if (args.length == 3) {
            String subCommand = args[0];
            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);
            if (target == null) return true;

            if (subCommand.equalsIgnoreCase("remove")) {
                GemConomy.removeBalance(p, target, amount);
                CurrencyData.saveCurrency();
            }
            if (subCommand.equalsIgnoreCase("add")) {
                GemConomy.addBalance(p, target, amount);
                CurrencyData.saveCurrency();
            }
            if (subCommand.equalsIgnoreCase("set")) {
                GemConomy.setBalance(p, target, amount);
                CurrencyData.saveCurrency();
            }
        }
        return true;
    }
}
