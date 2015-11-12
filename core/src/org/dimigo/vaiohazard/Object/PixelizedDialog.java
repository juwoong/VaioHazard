package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class PixelizedDialog extends Dialog{

    public PixelizedDialog(String title, Skin skin) {
        super(title, skin);
    }

    public PixelizedDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public PixelizedDialog(String title, Window.WindowStyle windowStyle) {
        super(title, windowStyle);
    }



    @Override
    protected void result(Object object){
        //boolean exit = (Boolean) object;
        String result = (String) object;
        if(result.equals("Exit")) Gdx.app.exit();
        else remove();

        System.out.println(result);
    }

    @Override
    public Dialog show(Stage stage) {
        return super.show(stage);
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public float getPrefHeight(){
        return 200f;
    }
}
