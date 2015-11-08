package org.dimigo.vaiohazard;

import com.badlogic.gdx.ApplicationAdapter;
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

public class VaioHazardGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Stage stage;
	Texture img;
	KnightActor knight;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("resources/vaio.png");
		knight = new KnightActor();
		knight.setPosition(0, 0);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			knight.setPosition(knight.getX() - 100 * dt, knight.getY());
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			knight.setPosition(knight.getX() + 100 * dt, knight.getY());
		}


		batch.begin();

		knight.draw(batch, 1);

		batch.draw(img, 16, 300);


		batch.end();
	}


}
