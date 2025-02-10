package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.ChatColor;

public class tagList {

    public enum Tags {
        UNSTOPPABLE(ChatColor.format("&cUNSTOPPABLE")),
        SPECIAL(ChatColor.format("&6SPECIAL")),
        UNKNOWN(ChatColor.format("&kII &cUNKNOWN &r&kII"));

        private final String tagContent;

        Tags(String tagContent) {
            this.tagContent = tagContent;
        }

        @Override
        public String toString() {
            return tagContent;
        }
    }
}
