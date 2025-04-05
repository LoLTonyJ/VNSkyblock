package me.tony.main.vnskyblock.Util;

import java.util.Random;

public class randomNumber {

    public static Integer generate(Integer max, Integer min) {
        Random num = new Random();
        return num.nextInt((max - min) + 1) + min;
    }

}
