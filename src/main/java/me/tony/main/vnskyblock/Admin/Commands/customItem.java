package me.tony.main.vnskyblock.Admin.Commands;

import me.tony.main.vnskyblock.CustomItems.ItemStacks.dirtWand;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.infiniteWaterbucket;
import me.tony.main.vnskyblock.CustomItems.ItemStacks.waterPumpBlock;
import me.tony.main.vnskyblock.IslandUtil.ItemStacks.CookieBuff;
import me.tony.main.vnskyblock.IslandUtil.ItemStacks.FlightSoup;
import me.tony.main.vnskyblock.Util.itemMetaEditor;
import me.tony.main.vnskyblock.Util.permCheck;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class customItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if (permCheck.isAdmin(p)) {
            if (args.length == 3) {
                // item edit lore/nick/color string
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("edit")) {
                    String toEdit = args[1];
                    if (toEdit.equalsIgnoreCase("lore")) {
                        itemMetaEditor.loreEdit(p.getInventory().getItemInMainHand(), args[2]);
                    }
                    if (toEdit.equalsIgnoreCase("name") || toEdit.equalsIgnoreCase("nick")) {
                        itemMetaEditor.nameChange(p.getInventory().getItemInMainHand(), args[2]);
                    }
                }
            }
            if (args.length == 1) {
                String item = args[0];
                if (item.equalsIgnoreCase("water") || item.equalsIgnoreCase("wb")) {
                    p.getInventory().addItem(infiniteWaterbucket.waterbucket());
                }
                if (item.equalsIgnoreCase("pump") || item.equalsIgnoreCase("waterpump")) {
                    p.getInventory().addItem(waterPumpBlock.waterPump());
                }
                if (item.equalsIgnoreCase("stew") || item.equalsIgnoreCase("flystew")) {
                    p.getInventory().addItem(FlightSoup.soup());
                }
                if (item.equalsIgnoreCase("biscuit")) {
                    p.getInventory().addItem(CookieBuff.biscuit());
                }
                if (item.equalsIgnoreCase("wand")) {
                    p.getInventory().addItem(dirtWand.dirtItem());
                }
            }
        }

        return true;
    }
}
