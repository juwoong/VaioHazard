package org.dimigo.vaiohazard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class VaioHazardGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Stage stage;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//stage = new Stage();
		img = new Texture("resources/vaio.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 16, 300);
		batch.end();
	}
}
