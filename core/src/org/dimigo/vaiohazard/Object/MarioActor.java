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

    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrame;
    private SpriteBatch spriteBatch;
    private TextureRegion currentFrame;

    private float stateTime;

    public MarioActor() {
        setBounds(0,0,100,100);
        walkSheet = new Texture(Gdx.files.internal("resources/Actor/mario.png"));
        TextureRegion[][] temp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        walkFrame = new TextureRegion[FRAME_COLS*FRAME_ROWS-1];

        int p = 0;
        for(int i=0; i<FRAME_ROWS; i++) {
            for(int j=0; j<FRAME_COLS; j++) {
                if(i==0 && j ==0) continue;
                walkFrame[p++] = temp[i][j];
            }
        }

        walkAnimation = new Animation(0.4f, walkFrame);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
