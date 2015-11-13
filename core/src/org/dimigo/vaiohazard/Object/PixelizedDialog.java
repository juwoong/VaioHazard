package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import org.dimigo.library.GameCoordinate;
import org.dimigo.vaiohazard.conversation.Question;

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

        //title padding
        padTop(70);
        padLeft(50);

        text("hey have some text Here!!\n hey have some text Here!!\nhey have some text Here!!여기 한글 되나용? \n", getTitleLabel().getStyle());

        getButtonTable().padBottom(30);
        getButtonTable().padRight(600);
    }


    @Override
    public Dialog button(String text, Object object, TextButton.TextButtonStyle buttonStyle) {
        String modifyText = " " + text + " ";
        getButtonTable().row();
        super.button(modifyText, object, buttonStyle);
        Array<Cell> cells= getButtonTable().getCells();
        cells.get(cells.size - 1).left();



        return this;
    }

    @Override
    protected void result(Object object){
        //boolean exit = (Boolean) object;
        //String result = (String) object;
        //if(result.equals("Exit")) Gdx.app.exit();
        //else remove();
        //System.out.println(result);
        if(object instanceof Question) {
            Question question = (Question) object;
            question.ask();
        }
        remove();
    }

    @Override
    public float getPrefWidth() {
        // force dialog width
        return 97 * GameCoordinate.RATIO_SCALE;
    }

    @Override
    public float getPrefHeight() {
        // force dialog height
        return 44 * GameCoordinate.RATIO_SCALE;
    }

    @Override
    public Dialog show(Stage stage) {
        return super.show(stage);
    }

    @Override
    public void cancel() {
        super.cancel();
    }
}