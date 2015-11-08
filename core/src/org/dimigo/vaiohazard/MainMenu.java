package org.dimigo.vaiohazard;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * Created by YuTack on 2015-11-08.
 */
public class MainMenu extends ScreenAdapter {

    Texture logo;
    SpriteBatch batch;
    Button newGameButton;
    Stage stage;

    @Override
    public void show() {
        stage = new Stage();

        batch = new SpriteBatch();
        logo = new Texture("resources/logo.png");

    }

    @Override
    public void render(float delta) {
        batch.begin();

        stage.act(delta);
        stage.draw();

        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(logo, 16, 300);

        batch.end();
    }
}
