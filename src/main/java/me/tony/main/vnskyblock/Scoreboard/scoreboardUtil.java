package me.tony.main.vnskyblock.Scoreboard;

import me.tony.main.vnskyblock.CurrencyUtil.PlayerData.gemConomy;
import me.tony.main.vnskyblock.PetUtil.DataManagement.playerOwnedPets;
import me.tony.main.vnskyblock.PlayerLevel.playerManager;
import me.tony.main.vnskyblock.PlayerTags.tagUtil;
import me.tony.main.vnskyblock.Tablist.tablistUtil;
import me.tony.main.vnskyblock.Util.ChatColor;
import me.tony.main.vnskyblock.VNSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class scoreboardUtil {

    public static void reloadScoreboard() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            initScoreboard(online);
            tablistUtil.setTabName(online);
        }
    }

    public static void initScoreboard(Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(VNSkyblock.getInstance(), new Runnable() {
            @Override
            public void run() {
                createScoreboard(p);
            }
        }, 1L, 1L);

    }

    public static void createScoreboard(Player p) {

        String scoreboardTitle = VNSkyblock.getInstance().getConfig().getString("scoreboard_title");
        String currencySymbol = VNSkyblock.getInstance().getConfig().getString("symbol");

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("Balance", "scoreboard");
        int gEco = gemConomy.getBalance(p);


        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.format(scoreboardTitle));

        // Player Data
        Score playerDisplayName = obj.getScore(ChatColor.format("&bName > " + p.getDisplayName()));
        playerDisplayName.setScore(15);
        Score playerLevel = obj.getScore(ChatColor.format("&bLevel > &7[&b⭐" + playerManager.getLevel(p) + "&7]"));
        playerLevel.setScore(14);
        Score playerProgress = obj.getScore(ChatColor.format("&bProgress > &7" + playerManager.getProgress(p) + "/500" ));
        playerProgress.setScore(13);
        Score playerBalance = obj.getScore(ChatColor.format("&bBalance > &a$" + VNSkyblock.getEconomy().getBalance(p)));
        playerBalance.setScore(12);
        Score premiumBalance = obj.getScore(ChatColor.format("&bGems > " + currencySymbol + gEco));
        premiumBalance.setScore(11);


        // Pet Data
        Score activePet = obj.getScore(ChatColor.format("&bActive Pet > " + playerOwnedPets.getActivePetName(p)));
        activePet.setScore(9);
        Score petExperience = obj.getScore(ChatColor.format("&aPet Experience > " + playerOwnedPets.getCurrentExperience(playerOwnedPets.getActivePet(p))));
        petExperience.setScore(8);



        // SPACERS
        Score titleSpace = obj.getScore(org.bukkit.ChatColor.BLACK + "");
        titleSpace.setScore(16);
        Score space2 = obj.getScore(org.bukkit.ChatColor.BLUE + " ");
        space2.setScore(10);


        // Tablist
        Objective prefixTab = scoreboard.registerNewObjective("prefix", "tablist");
        prefixTab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        prefixTab.setDisplayName(p.getDisplayName());

        // Tags
        if (tagUtil.getPlayerTag(p) != null) {
        Objective tagObj = scoreboard.registerNewObjective("tags", "username");
        tagObj.setDisplaySlot(DisplaySlot.BELOW_NAME);
            tagObj.setDisplayName(ChatColor.format(tagUtil.getPlayerTag(p)));
        }

        p.setScoreboard(scoreboard);
    }



}
