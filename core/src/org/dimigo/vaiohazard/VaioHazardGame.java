package org.dimigo.vaiohazard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.dimigo.vaiohazard.object.ServiceCenter;

public class VaioHazardGame extends Game {
	/*SpriteBatch batch;
	//Stage stage;
	Texture img;
	KnightActor knight;
	boolean flip = false;*/
	public MainMenu mainMenu;
	public GameScreen gameScreen;
	public LoadingScreen loadingScreen;

	public ServiceCenter serviceCenter;

	//80 * 4,  45 * 4


	@Override
	public void create () {
		mainMenu = new MainMenu(this);
		gameScreen = new GameScreen(this);
		loadingScreen = new LoadingScreen(this);

		setScreen(mainMenu);
	}

	@Override
	public void render () {
		super.render();
		getScreen().render(Gdx.graphics.getDeltaTime());
	}


}
