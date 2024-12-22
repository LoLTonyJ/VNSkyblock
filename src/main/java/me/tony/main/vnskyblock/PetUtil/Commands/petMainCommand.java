package me.tony.main.vnskyblock.PetUtil.Commands;

import me.tony.main.vnskyblock.PetUtil.Inventories.petDisplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class petMainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        Player p = (Player) sender;

        if (args.length == 0) {
            petDisplay.petInventory(p);
        }


        return true;
    }
}
