package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class tagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatColor.format("&bUsage: /tag set <tag>"));
            p.sendMessage(ChatColor.format("&bUsage: /tag list"));
        }

        if (args.length == 1) {
            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("list")) {
                if (tagUtil.playerOwnedTags(p) == null) {
                    p.sendMessage(ChatColor.format("&cYou do not own any tags."));
                } else {
                    List<String> ownedTags = tagUtil.playerOwnedTags(p);
                    p.sendMessage(ChatColor.format("&bHeres a list of Tags you own!"));
                    for (String s : ownedTags) {
                        p.sendMessage(ChatColor.format(String.valueOf(s)));
                    }
                }
            }
        }
        if (args.length == 2) {
            String subCommand = args[0];
            String tagSet = args[1].toUpperCase();
            if (subCommand.equalsIgnoreCase("set")) {
                tagList.Tags tagName = tagList.Tags.valueOf(tagSet);
                String tagContent = tagList.Tags.valueOf(tagSet).toString();
                if (tagUtil.playerOwnedTags(p).contains(tagContent)) {
                    tagUtil.setTag(p, tagName);
                    p.sendMessage(ChatColor.format("&bYou've set your tag to " + tagContent));
                } else {
                    p.sendMessage(ChatColor.format("&cTag not found! &b/tag list"));
                }
            }
        }

        if (permCheck.isAdmin(p)) {
            if (args.length == 3) {
                String subCommand = args[0];
                String tagSet = args[1].toUpperCase();
                Player target = Bukkit.getPlayer(args[2]);
                if (subCommand.equalsIgnoreCase("add")) {
                    if (target == null) return true;
                    tagList.Tags tagName = tagList.Tags.valueOf(tagSet);
                    tagUtil.addTag(target, tagName.toString());
                }
            }
        }





        return true;
    }
}
