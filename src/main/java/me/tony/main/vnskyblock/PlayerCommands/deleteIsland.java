package me.tony.main.vnskyblock.PlayerCommands;

import me.tony.main.vnskyblock.IslandUtil.IslandPlayerManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class deleteIsland implements CommandExecutor {

    private static final ArrayList<UUID> confirmIslandDelete = new ArrayList<>();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (!confirmIslandDelete.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.format("&c&lAre you sure you want to delete your island? Re-run the command to confirm"));
            confirmIslandDelete.add(p.getUniqueId());
        } else {
            IslandPlayerManager.removePlayerIsland(p);
            confirmIslandDelete.remove(p.getUniqueId());
        }


        return true;
    }
}
