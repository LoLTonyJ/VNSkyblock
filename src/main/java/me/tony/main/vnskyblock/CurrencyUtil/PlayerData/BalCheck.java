package me.tony.main.vnskyblock.CurrencyUtil.PlayerData;

import me.tony.main.vnskyblock.Util.ChatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalCheck implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String symbol = VNSkyblock.getInstance().getConfig().getString("symbol");

        Player p = (Player) sender;
        p.sendMessage(ChatUtil.format("&bGems:" + " " + symbol + " " + GemConomy.getBalance(p)));
        p.sendMessage(ChatUtil.format("&aBalance: " + " $" + VNSkyblock.getEconomy().getBalance(p)));

        return true;
    }
}
