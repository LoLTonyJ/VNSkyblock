package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Admin.FileManagement.spawnFile;
import me.tony.main.vnskyblock.Admin.Methods.Spawn;
import me.tony.main.vnskyblock.Util.debug;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class spawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Location loc = Spawn.getSpawn();
        p.teleport(loc);
        return true;
    }
}
