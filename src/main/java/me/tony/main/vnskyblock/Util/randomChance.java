package me.tony.main.vnskyblock.Util;

import java.util.Random;

public class randomChance {

    public static int randomNumber(int min, int max) {
        Random num = new Random();
        return num.nextInt((max - min) + 1) + min;
    }

    public static boolean chance(int randomResult, int lessThan) {
        return randomResult < lessThan;
    }


}
