package me.tony.main.vnskyblock.Util;

import java.util.List;

public class placeholder {

    public static String replace(String placeholder, String replaceWith, String msg) {
        return msg.replace(placeholder, replaceWith);
    }

    public static List<String> replaceListPlaceholder(List<String> list, String placeholder, String toReplace) {

        List<String> checkList = list;
        for (String s : checkList) {
            String replacedString = s.replace(placeholder, toReplace);
            checkList.add(replacedString);
        }
        return checkList;
    }


}
