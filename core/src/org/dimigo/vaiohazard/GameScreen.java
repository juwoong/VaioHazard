package org.dimigo.vaiohazard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import org.dimigo.library.DialogGenerator;
import org.dimigo.library.GameCoordinate;
import org.dimigo.vaiohazard.Object.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.dimigo.library.NameGenerator;
import org.dimigo.vaiohazard.Object.Customer;
import org.dimigo.vaiohazard.conversation.Conversation;

/**
 * Created by YuTack on 2015-11-09.
 */
public class GameScreen extends ScreenAdapter {
    VaioHazardGame currentGame;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    Stage stage;
    Music music;

    boolean cleaned = false;

    public GameScreen(VaioHazardGame game) {
        this.currentGame = game;

        init();
    }
    private void init() {
        stage = new Stage();

        ServiceCenter.getInstance().setCurrentStage(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera.update();
        tiledMap = new TmxMapLoader().load("resources/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        NameGenerator nameGenerator = NameGenerator.getInstance();
        //ConversationParser parser = new ConversationParser(stage);

        Button newGameButton;
        Button.ButtonStyle buttonStyle;
        TextureAtlas buttonAtlas;
        Skin skin;

        Customer test = new Customer(nameGenerator.getName(), 1, "mario.png", 4, 1);
        test.setPosition(100, 400);
        final Conversation conversation = new Conversation(stage, test);

        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = GameResource.getInstance().getDrawable("new_button");
        buttonStyle.down = GameResource.getInstance().getDrawable("new_button_pressed");
        newGameButton = new Button(buttonStyle);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                conversation.start();
            }
        });
        //stage.addActor(img);
        stage.addActor(newGameButton);


        //stage.addActor(mario);
        stage.addActor(test);
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

        music = Gdx.audio.newMusic(Gdx.files.internal("resources/music/game.mp3"));
        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();

        ServiceCenter.getInstance().updateDate();

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

        ServiceCenter.getInstance().update(delta);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.draw();

    }
}
