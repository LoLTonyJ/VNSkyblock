package me.tony.main.vnskyblock.Minions.Commands;

import me.tony.main.vnskyblock.Minions.ItemStacks.cobblestoneMinion;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            // /minion give <player> <minion_type> <tier>
            if (args.length == 4) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    String minionType = args[2];
                    Integer tier = Integer.parseInt(args[3]);
                    if (minionType.equalsIgnoreCase("cobble")) {
                        target.getInventory().addItem(cobblestoneMinion.cobbleMin(tier));
                    }
                }
            }
        }

        return true;
    }
}
