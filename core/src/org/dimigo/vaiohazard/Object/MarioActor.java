package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
