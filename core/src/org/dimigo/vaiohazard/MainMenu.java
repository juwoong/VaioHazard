package org.dimigo.vaiohazard;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//1280 720

/**
 * Created by YuTack on 2015-11-08.
 */
public class MainMenu implements Screen {
    Image logo;
    Stage stage;

    Button newGameButton;
    Button.ButtonStyle buttonStyle;
    TextureAtlas buttonAtlas;
    Skin skin;

    VaioHazardGame currentGame;

    Table table;

    public MainMenu(VaioHazardGame game) {
        currentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        table = new Table();
        table.setFillParent(true);

        logo = new Image(new Texture("resources/logo.png"));
        logo.setPosition(16, 300);

        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        buttonAtlas = new TextureAtlas("resources/Button/NewButton.pack");
        skin.addRegions(buttonAtlas);
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.getDrawable("NewButton");
        buttonStyle.down = skin.getDrawable("NewButton_Pressed");
        newGameButton = new Button(buttonStyle);
        newGameButton.addListener( new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                currentGame.setScreen(currentGame.gameScreen);
            }
        });

        table.row();
        table.add(newGameButton).padRight(80).width(150).height(70);
        table.right();

        stage.addActor(table);
        stage.addActor(logo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void hide() {
        stage.clear();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }
}
