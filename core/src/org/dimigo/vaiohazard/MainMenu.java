package org.dimigo.vaiohazard;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import javafx.scene.control.ColorPickerBuilder;
import org.dimigo.library.FontGenerater;
import org.dimigo.vaiohazard.Object.ServiceCenter;

import javax.imageio.stream.ImageInputStream;
import java.time.format.TextStyle;
//1280 720

/**
 * Created by YuTack on 2015-11-08.
 */
public class MainMenu implements Screen {
    Stage stage;

    VaioHazardGame currentGame;

    Table table;

    FontGenerater generater = new FontGenerater();
    SpriteBatch batch = new SpriteBatch();

    public MainMenu(VaioHazardGame game) {  currentGame = game; }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        //table.row();
        table.right();

        mainInit();
        buttonInit();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        batch.begin();
        generater.drawBitmapFont(batch, 30, "Developed By 박유택, 배주웅", Color.WHITE, 10, 40);
        batch.end();
        generater.releaseAll();
    }

    private void mainInit() {
        Image logo;

        logo = new Image(new Texture("resources/Ui/logo.png"));
        logo.setPosition(16, 300);
        logo.setOrigin(logo.getWidth() / 2, logo.getHeight() / 2);
        RepeatAction twinkleForever = new RepeatAction();
        twinkleForever.setAction(new SequenceAction(CustomActions.twinkle(), new DelayAction(1.3f)));
        twinkleForever.setCount(RepeatAction.FOREVER);

        logo.addAction(twinkleForever);

        stage.addActor(logo);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();



        //TextField text = new TextField("Developed by 박유택, 배주웅",);
    }

    private void buttonInit() {
        Button newGameButton;
        Button.ButtonStyle buttonStyle;
        TextureAtlas buttonAtlas;
        Skin skin;

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
                //currentGame.setScreen(currentGame.gameScreen);
                ServiceCenter.newCenter();
                currentGame.setScreen(currentGame.loadingScreen);
            }
        });

        Button loadGameButton;
        buttonAtlas = new TextureAtlas("resources/Button/LoadButton.pack");
        skin.addRegions(buttonAtlas);
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.getDrawable("LoadButton");
        buttonStyle.down = skin.getDrawable("LoadButton_Pressed");
        loadGameButton = new Button(buttonStyle);
        loadGameButton.addListener( new InputListener() {
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //load logic
            }
        });

        table.add(newGameButton).padRight(80).padBottom(20).width(150).height(70);
        table.row();
        table.add(loadGameButton).padRight(80).width(150).height(70);

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
