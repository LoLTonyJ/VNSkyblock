package me.tony.main.vnskyblock.CurrencyUtil.PlayerData;

import me.tony.main.vnskyblock.Util.ChatUtil;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class GemConomy implements Listener {

    public static HashMap<UUID, Integer> PlayerData = new HashMap<>(); // This is your main Map for Player Data.
    private static int start = VNSkyblock.getInstance().getConfig().getInt("start_balance");
    private static int min = VNSkyblock.getInstance().getConfig().getInt("min_amount");
    private static int max = VNSkyblock.getInstance().getConfig().getInt("max_amount");

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        InitCurrency(e.getPlayer());
    }


    public static int getBalance(Player p) {
        if (PlayerData.containsKey(p.getUniqueId())) {
            return PlayerData.get(p.getUniqueId());
        }
        return start;
    }

    public static void InitCurrency(Player p) {
        if (PlayerData.containsKey(p.getUniqueId())) return;
        PlayerData.put(p.getUniqueId(), start);
    }


    public static void removeBalance(CommandSender sender, Player p, int amount) {

        if (!p.isOnline()) return;

        int balance = PlayerData.get(p.getUniqueId());

        if (getBalance(p) < amount) {
            sender.sendMessage(ChatUtil.format("&cThat player doesn't have that much!"));
            return;
        }
        if (amount < min) {
            sender.sendMessage(ChatUtil.format("&cYou cannot remove " + amount));
            return;
        }

        if (amount - PlayerData.get(p.getUniqueId()) < min) {
            sender.sendMessage(ChatUtil.format("&cInvalid! Please try a smaller number!"));
            return;
        }
        PlayerData.replace(p.getUniqueId(), PlayerData.get(p.getUniqueId()), balance - amount);
        sender.sendMessage(ChatUtil.format("&aSet " + p.getName() + "'s balance to " + getBalance(p)));
    }

    public static void addBalance(CommandSender sender, Player p, int amount) {
        if (!p.isOnline()) return;

        if (!PlayerData.containsKey(p.getUniqueId())) {
            InitCurrency(p);
        }

        int balance = PlayerData.get(p.getUniqueId());

        if (balance + amount > max) {
            sender.sendMessage(ChatUtil.format("&cPlease try a smaller number!"));
            return;
        }
        if (amount > max) {
            sender.sendMessage(ChatUtil.format("&cPlease try a smaller number!"));
            return;
        }
        PlayerData.replace(p.getUniqueId(), PlayerData.get(p.getUniqueId()), balance + amount);
        sender.sendMessage(ChatUtil.format("&aSet " + p.getName() + "'s balance to " + getBalance(p)));
    }

    public static void setBalance(CommandSender sender, Player p, int amount) {
        if (!p.isOnline()) return;
        int balance = PlayerData.get(p.getUniqueId());

        if (amount > max) {
            sender.sendMessage(ChatUtil.format("&cTry a smaller number!"));
            return;
        }
        PlayerData.replace(p.getUniqueId(), balance, amount);
        sender.sendMessage(ChatUtil.format("&aSet " + p.getName() + "'s balance to " + getBalance(p)));
    }

    public static void resetBalance(CommandSender sender, Player p) {
        if (!p.isOnline()) return;
        PlayerData.replace(p.getUniqueId(), PlayerData.get(p.getUniqueId()), start);
        sender.sendMessage(ChatUtil.format("&aReset " + p.getName() + "'s balance!"));
    }


}
