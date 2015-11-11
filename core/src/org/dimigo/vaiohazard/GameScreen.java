package org.dimigo.vaiohazard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.dimigo.library.DialogGenerater;
import org.dimigo.vaiohazard.Object.MarioActor;
import org.dimigo.vaiohazard.Object.PixelizedDialog;

/**
 * Created by YuTack on 2015-11-09.
 */
public class GameScreen extends ScreenAdapter {
    VaioHazardGame currentGame;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Stage stage;
    MarioActor mario;
    PixelizedDialog dialog;

    public GameScreen(VaioHazardGame game) {
        this.currentGame = game;
        stage = new Stage();

        mario = new MarioActor();
        mario.setPosition(30, 30);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera.update();
        tiledMap = new TmxMapLoader().load("resources/Ui/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        Image img = new Image(new Texture("resources/Ui/Dialog_.png"));
        img.setScale(GameCoordinate.RATIO);
        img.setPosition(GameCoordinate.toRealPos(28), GameCoordinate.toRealPos(20));

        dialog = (new DialogGenerater()).getDialog("이것은폰트입니다 씨발려나");

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
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.show(stage);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        dialog.hide();
                    }
                }, 10);
            }
        });

        //stage.addActor(img);
        stage.addActor(newGameButton);
        stage.addActor(mario);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(delta);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.draw();

    }
}
