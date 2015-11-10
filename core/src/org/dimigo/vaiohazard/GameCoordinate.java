package org.dimigo.vaiohazard;

/**
 * Created by YuTack on 2015-11-10.
 */
public class GameCoordinate {
    public static int RATIO = 8;

    public static int toRealPos(int position) { return position * RATIO; }
}
