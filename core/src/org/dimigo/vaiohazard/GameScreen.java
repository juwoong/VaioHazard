package org.dimigo.vaiohazard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by YuTack on 2015-11-09.
 */
public class GameScreen extends ScreenAdapter {
    VaioHazardGame currentGame;

    Stage stage;

    public GameScreen(VaioHazardGame game) {
        this.currentGame = game;
        stage = new Stage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}
