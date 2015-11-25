package org.dimigo.vaiohazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import org.dimigo.library.GameCoordinate;
import org.dimigo.vaiohazard.Object.Customer;

/**
 * Created by YuTack on 2015-11-09.
 */
public class GameScreen extends ScreenAdapter {
    VaioHazardGame currentGame;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Stage stage;

    boolean cleaned = false;

    public GameScreen(VaioHazardGame game) {
        this.currentGame = game;

        init();
    }
    private void init() {
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera.update();
        tiledMap = new TmxMapLoader().load("resources/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void show() {
        Gdx.input.setCatchMenuKey(true);
        Gdx.input.setCatchBackKey(true);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        Image clerkTester = new Image(new Texture("resources/Actor/Creeper.png"));
        clerkTester.setScale(0.6f, 0.6f);
        clerkTester.setPosition(GameCoordinate.toRealPos(105), GameCoordinate.toRealPos(105));

        Customer customer = new Customer("첫번째손님!", true);

        stage.addActor(customer);
        stage.addActor(clerkTester);
    }

    @Override
    public void render(float delta) {
        if(cleaned == true) {
            stage.setDebugAll(false);
        }
        if(cleaned == false) {
            stage.setDebugAll(true);
            cleaned = true;
        }

        Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);



        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.draw();

    }
}
