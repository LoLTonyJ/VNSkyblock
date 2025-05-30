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

        if (!(sender instanceof Player)) {
            if (args.length == 2) {
                String playerName = args[1];
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("unban")) {
                    PunishManager.removeBanConsole(playerName);
                    return true;
                }
            }
        }

        Player p = (Player) sender;
        if (permCheck.isHelper(p) || permCheck.isMod(p) || permCheck.isAdmin(p)) {
            if (args.length == 1) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("toggle") || subCommand.equalsIgnoreCase("mute")) {
                    PunishManager.togglePunishLogs(p);
                    return true;
                }
            }
        }
        if (permCheck.isAdmin(p)) {
            if (args.length == 1) {
                // /punish player
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(ChatColor.format("&cThat player is offline!"));
                } else {
                    PunishGUI.setVictim(p, target);
                    PunishGUI.punishMainInventory(p);
                    return true;
                }
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String subCommand = args[1];
                if (subCommand.equalsIgnoreCase("unban")) {
                    PunishManager.removeBan(p, target);
                    return true;
                }
            }
        }
        return true;
    }
}
