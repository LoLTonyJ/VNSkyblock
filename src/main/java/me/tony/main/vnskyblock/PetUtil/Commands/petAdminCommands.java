package me.tony.main.vnskyblock.PetUtil.Commands;

import me.tony.main.vnskyblock.PetUtil.DataManagement.petItems;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.permCheck;
import me.tony.main.vnskyblock.Util.rarityUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class petAdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            // pa create <pet_name> <pet_ability> <rarity>
            // pa give <player> <pet> <rarity>

            if (args.length < 3) {
                p.sendMessage(chatUtil.format("&b/pa create <pet_name> <pet_ability> <rarity>"));
                p.sendMessage(chatUtil.format("&b/pa give <player> <pet> <rarity>"));
                p.sendMessage(chatUtil.format("&b/pa list"));
            }

            if (args.length == 4) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    String pet = args[2];
                    rarityUtil.Rarity rarity = rarityUtil.Rarity.valueOf(args[3].toUpperCase());
                    if (!target.isOnline()) {
                        p.sendMessage(chatUtil.format("&cPlayer is Offline!"));
                        return true;
                    }
                    if (pet.equalsIgnoreCase("monkey")) {
                        p.getInventory().addItem(petItems.MonkeyPet(rarity, 1));
                    }
                    if (pet.equalsIgnoreCase("rock")) {
                        p.getInventory().addItem(petItems.RockPet(rarity, 1));
                    }
                }

            }


        }


        return true;
    }
}
