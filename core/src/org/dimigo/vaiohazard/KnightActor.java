package org.dimigo.vaiohazard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by YuTack on 2015-11-08.
 */
public class KnightActor extends Actor{
    private TextureRegion body = new TextureRegion(new Texture("resources/Knight.png"));



    public KnightActor() {
        setBounds(0, 0, 100, 100);
    }



    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(body, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    @Override
    public void act (float delta) {
        super.act(delta);
    }
}
