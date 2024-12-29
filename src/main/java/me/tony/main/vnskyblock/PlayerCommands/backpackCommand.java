package me.tony.main.vnskyblock.PlayerCommands;

import me.tony.main.vnskyblock.PlayerCommands.Methods.backPackMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class backpackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            backPackMethods.playerBackpack(p);
        }

        return true;
    }
}
