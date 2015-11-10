package org.dimigo.vaiohazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.dimigo.library.FontGenerater;
import org.dimigo.vaiohazard.Object.MarioActor;

import java.awt.*;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class LoadingScreen extends ScreenAdapter {
    VaioHazardGame currentGame;
    Stage stage;
    MarioActor mario;
    SpriteBatch batch;

    public LoadingScreen(VaioHazardGame game) {
        this.currentGame = game;
        stage = new Stage();
        batch = new SpriteBatch();

        mario = new MarioActor();
        mario.setPosition(30, 30);

        stage.addActor(mario);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FontGenerater generater = new FontGenerater();

        stage.act(delta);

        batch.begin();

        generater.drawBitmapFont(batch, 30, "Developed By 박유택, 배주웅", Color.BLACK, 70, 40);

        batch.end();

        generater.releaseAll();
        stage.draw();
    }
}
