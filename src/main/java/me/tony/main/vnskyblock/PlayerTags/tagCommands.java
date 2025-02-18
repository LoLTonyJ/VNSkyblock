package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class tagCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            tagInventory.displayTags(p);
        }

        if (permCheck.isAdmin(p)) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.format("&bUsage: /tag <set/remove/add/create/remove> <player/tag name> <tag/content>"));
            }
            // tag create <tag> <content>
            if (args.length == 2) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("delete") || subCommand.equalsIgnoreCase("remove")) {
                    String tagName = args[1];
                    if (tagData.isCreated(tagName)) {
                        p.sendMessage(ChatColor.format("&cTag already exists!"));
                        return true;
                    }
                    tagData.removeTag(tagName);
                    p.sendMessage(ChatColor.format("&aRemoved tag; " + tagName + " from Server DB!"));
                }
            }
            if (args.length == 3) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("create")) {
                    String tagName = args[1];
                    String content = args[2];
                    if (tagData.isCreated(tagName)) {
                        p.sendMessage(ChatColor.format("&cTag already exists!"));
                        return true;
                    }
                    tagData.createTag(tagName, content);
                    p.sendMessage(ChatColor.format("&aTag Created; " + tagName));
                }
                if (subCommand.equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.format(target + " &7is not online!"));
                        return true;
                    }
                }

            }
        }

        return true;
    }
}
