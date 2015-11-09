package org.dimigo.vaiohazard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import org.dimigo.library.FontGenerater;

public class VaioHazardGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Stage stage;
	Texture img;
	KnightActor knight;
	FontGenerater generater;
	boolean flip = false;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("resources/logo.png");
		knight = new KnightActor();
		generater = new FontGenerater();
		knight.setPosition(0, 0);
	}

	@Override
	public void render () {
		//BitmapFont font = generater.getBitmapFont(24, "Developedby박유택,배주웅", Color.WHITE);
		float dt = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(92 / 255f, 167 / 255f, 244 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//boolean flip = false;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            knight.setPosition(knight.getX() - 100 * dt, knight.getY());
            //knight.draw(batch, 1, true);
            flip = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            knight.setPosition(knight.getX() + 100 * dt, knight.getY());
            flip = false;
        }

        batch.begin();

        knight.draw(batch, 1, flip);
		//font.draw(batch, "Developed by 박유택, 배주웅", 50, 300);
		generater.drawBitmapFont(batch, 25, "Developed by 박유택, 배주웅", Color.WHITE, 50, 300);
		batch.draw(img, 16, 300);

		batch.end();
		//font.dispose();

		generater.releaseAll();
	}


}
