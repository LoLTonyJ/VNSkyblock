package me.tony.main.vnskyblock.PetUtil.Commands;

import me.tony.main.vnskyblock.PetUtil.DataManagement.petItems;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PetUtil.Inventories.playerPetDisplay;
import me.tony.main.vnskyblock.Util.chatUtil;
import me.tony.main.vnskyblock.Util.debug;
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

        if (args.length == 1) {
            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("uuid")) {
                if (playerOwnedPets.getActivePet(p) != null) {
                    debug.print(petItems.getItemUUID(playerOwnedPets.getActivePet(p)));
                }
            }
        }

        if (permCheck.isHelper(p)) {
            // pa view <player>
            if (args.length == 0) {
                p.sendMessage(chatUtil.format("&b/pa view <player>"));
            }

            if (args.length == 2) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("view")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage(chatUtil.format("&cPlayer does not exist!"));
                        return true;
                    }
                    playerPetDisplay.displayPlayerPets(p, target);
                }
            }
        }


        if (permCheck.isAdmin(p)) {
            // pa create <pet_name> <pet_ability> <rarity>
            // pa give <player> <pet> <rarity>

            if (args.length < 3) {
                p.sendMessage(chatUtil.format("&b/pa create <pet_name> <pet_ability> <rarity>"));
                p.sendMessage(chatUtil.format("&b/pa give <player> <pet> <rarity>"));
                p.sendMessage(chatUtil.format("&b/pa list"));
            }

            if (args.length == 3) {
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("edit")) {
                    String toEdit = args[1];
                    if (toEdit.equalsIgnoreCase("level") || toEdit.equalsIgnoreCase("lvl")) {
                        int level = Integer.parseInt(args[2]);
                        playerOwnedPets.setPetLevel(p, p.getInventory().getItemInMainHand(), level);
                    }
                }
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
