package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by YuTack on 2015-11-10.
 */
public class GWindow extends Actor{

    private Stage content;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        content.draw();
    }

    @Override
    public void act(float delta) {

    }
}
