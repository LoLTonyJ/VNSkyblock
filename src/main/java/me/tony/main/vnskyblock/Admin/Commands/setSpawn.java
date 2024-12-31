package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Admin.Methods.Spawn;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class setSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (permCheck.isAdmin(p)) {
            Spawn.setSpawn(p);
        }
        return true;
    }
}
