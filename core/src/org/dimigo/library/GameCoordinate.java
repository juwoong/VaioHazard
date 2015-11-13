package org.dimigo.library;

/**
 * Created by YuTack on 2015-11-10.
 */
public class GameCoordinate {
    public static final int RATIO = 4;
    public static final int RATIO_SCALE = 8;
    public static final int pixelWidth = 320;
    public static final int pixelHeight = 180;

    public static int toRealPos(int position) { return position * RATIO; }
}
