package org.dimigo.vaiohazard;

/**
 * Created by YuTack on 2015-11-10.
 */
public class GameCoordinate {
    public static final int RATIO = 8;
    public static final int pixelWidth = 320;
    public static final int pixelHeight = 180;

    public static int toRealPos(int position) { return position * RATIO; }
}
