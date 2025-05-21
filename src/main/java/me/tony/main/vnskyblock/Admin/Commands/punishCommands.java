package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.Admin.FileManipulation.punishConfiguration;
import me.tony.main.vnskyblock.Admin.GUI.PunishGUI;
import me.tony.main.vnskyblock.Admin.PlayerManager.PunishManager;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class punishCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (permCheck.isAdmin(p)) {
            if (args.length == 1) {
                // /punish player
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.format("&cThat player is offline!"));
                } else {
                    PunishGUI.setVictim(p, target);
                    PunishGUI.punishMainInventory(p);
                }
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String subCommand = args[1];
                if (subCommand.equalsIgnoreCase("unban")) {
                    PunishManager.removeBan(p, target);
                }
            }
        }
        return true;
    }
}
