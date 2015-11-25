package org.dimigo.vaiohazard.Object;

/**
 * Created by juwoong on 15. 11. 9..
 */
public class MarioActor extends VaioActor {
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 1;

    public MarioActor() {
        setAnimation("mario.png", FRAME_COLS, FRAME_ROWS);
    }


}
