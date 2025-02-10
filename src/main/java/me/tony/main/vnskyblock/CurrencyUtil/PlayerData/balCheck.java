package me.tony.main.vnskyblock.CurrencyUtil.PlayerData;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class balCheck implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String symbol = VNSkyblock.getInstance().getConfig().getString("symbol");

        Player p = (Player) sender;
        p.sendMessage(ChatColor.format("&bGems:" + " " + symbol + " " + gemConomy.getBalance(p)));
        p.sendMessage(ChatColor.format("&aBalance: " + " $" + VNSkyblock.getEconomy().getBalance(p)));

        return true;
    }
}
