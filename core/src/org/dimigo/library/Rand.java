package org.dimigo.library;

import java.util.Random;

/**
 * Created by YuTack on 2015-11-25.
 */
public class Rand {
    public static Random random = new Random();
    public static boolean get(float p) {
        return (random.nextFloat() < p);
    }

    //0-100
    public static boolean get(int p) {
        return get((float)p / 100);
    }
}
