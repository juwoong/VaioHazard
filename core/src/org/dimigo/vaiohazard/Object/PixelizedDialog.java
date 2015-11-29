package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import org.dimigo.library.GameCoordinate;
import org.dimigo.library.ImpairSelector;
import org.dimigo.vaiohazard.conversation.Answer;
import org.dimigo.vaiohazard.conversation.Conversation;
import org.dimigo.vaiohazard.conversation.Question;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class PixelizedDialog extends Dialog{

    private static BitmapFont textFont, titleFont;
    private static Skin skin;
    private static Window.WindowStyle windowStyle;
    private static TextButton.TextButtonStyle style;
    private static Label.LabelStyle labelStyle;
    private static Label label;
    private Conversation conv;
    static {

    }

    public PixelizedDialog(String title, Skin skin) {
        super(title, skin);
    }

    public PixelizedDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public PixelizedDialog(String title, Window.WindowStyle windowStyle, Conversation conv) {
        super(title, windowStyle);

        //디자인합니당 이야잉양 패딩패딩 왼쪽그리고위
        getContentTable().left().padLeft(50).padTop(20);
        getTitleTable().padTop(110).padLeft(60);

        setMovable(true);

        getButtonTable().left();
        getButtonTable().padBottom(50);
        //getButtonTable().padRight(600);
        this.conv=conv;
    }

    public Dialog button(String text, Object object, TextButton.TextButtonStyle buttonStyle, boolean rowButton) {
        if(rowButton == true)
            getButtonTable().row().left();

        super.button(text, object, buttonStyle);
        Array<Cell> cells= getButtonTable().getCells();
        cells.get(cells.size - 1).left();

        return this;
    }

    @Override
    protected void result(Object object){
        //((Conversation) object).listenAnswer();
        if(object == null) {
            conv.listenAnswer(null);
        } else if (object instanceof ImpairSelector) {
            conv.listenAnswer(((ImpairSelector) object).getResult());
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