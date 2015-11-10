package org.dimigo.vaiohazard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

public class VaioHazardGame extends Game {
	/*SpriteBatch batch;
	//Stage stage;
	Texture img;
	KnightActor knight;
	boolean flip = false;*/
	public MainMenu mainMenu;
	public GameScreen gameScreen;

	//80 * 4,  45 * 4
	static final int pixelWidth = 320;
	static final int pixelHeight = 180;

	@Override
	public void create () {

		mainMenu = new MainMenu(this);
		gameScreen = new GameScreen(this);

		setScreen(mainMenu);
	}

	@Override
	public void render () {
		super.render();
		getScreen().render(Gdx.graphics.getDeltaTime());
	}


}
