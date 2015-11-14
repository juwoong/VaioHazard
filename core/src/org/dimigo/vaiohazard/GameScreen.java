package org.dimigo.vaiohazard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import org.dimigo.library.ConversationParser;
import org.dimigo.library.DialogGenerater;
import org.dimigo.library.GameCoordinate;
import org.dimigo.library.NameGenerator;
import org.dimigo.vaiohazard.Object.MarioActor;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.conversation.Conversation;
import org.dimigo.vaiohazard.conversation.Question;

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

        NameGenerator nameGenerator = NameGenerator.getInstance();
        ConversationParser parser = new ConversationParser(stage);

        Button newGameButton;
        Button.ButtonStyle buttonStyle;
        TextureAtlas buttonAtlas;
        Skin skin;

        final Conversation conversation = new Conversation(stage);
        final Conversation conv2 = parser.parse("resources/conversation/question.json");


        Question q1 = new Question("넌 정말 엄마가 없구나!", "응 니애미");
        Question q2 = new Question("오... 당신은 패드립 마스터입니다.", "너 내 동생이구나!");
        Question q3 = new Question("너희엄마는 미국이 아니라 하늘나라 가셨겠지!", "뭐 임마");
        q3.insertQuestion(q1);
        q3.insertQuestion(q2);
        Question q4 = new Question("오, 장난이었는데.... 미안해.... 바이오같은새끼", "예");
        Question q5 = new Question("한국 디지털 미디어 고등학교 사무국장 같다", "그렇습니다.");
        Question q6 = new Question("너 엄마 없지?", "null");
        q6.insertQuestion(q4);
        q6.insertQuestion(q5);
        q6.insertQuestion(q3);
        conversation.setQuestion(q6);
        conversation.setStage();
        conversation.setName(nameGenerator.getName());


        conv2.setStage();
        conv2.setName(nameGenerator.getName());

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
                conversation.start();
            }
        });

        //stage.addActor(img);
        stage.addActor(newGameButton);
        stage.addActor(mario);

    }

    @Override
    public void show() {
        Gdx.input.setCatchMenuKey(true);
        Gdx.input.setCatchBackKey(true);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
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
