package me.tony.main.vnskyblock.PlayerTags;

import me.tony.main.vnskyblock.Util.chatUtil;

public class tagList {

    public enum Tags {
        UNSTOPPABLE(chatUtil.format("&cUNSTOPPABLE")),
        SPECIAL(chatUtil.format("&6SPECIAL")),
        UNKNOWN(chatUtil.format("&kII &cUNKNOWN &r&kii"));

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
