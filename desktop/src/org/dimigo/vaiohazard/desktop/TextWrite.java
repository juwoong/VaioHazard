package org.dimigo.vaiohazard.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by juwoong on 15. 11. 7..
 */
public class TextWrite implements ApplicationListener{
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.NAVY);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Hello libGDX!", 200, 200);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


}
