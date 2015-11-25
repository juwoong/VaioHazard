package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import org.dimigo.vaiohazard.device.Components;
import org.dimigo.vaiohazard.GameResource;
import org.dimigo.vaiohazard.object.PixelizedDialog;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class DialogGenerator {
    private FontGenerator generater;
    private BitmapFont textFont, titleFont;
    private Window.WindowStyle windowStyle;
    private TextButton.TextButtonStyle textButtonStyle;
    private RightCheckBox.CheckBoxStyle checkBoxStyle;
    private Label label;

    public DialogGenerator() {
        generater = new FontGenerator();

        textFont = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("resources/font/font_big.fnt"));

        windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = titleFont;

        //이거 동적으로
        windowStyle.titleFontColor = Color.NAVY;
        //

        windowStyle.background = new NinePatchDrawable(GameResource.getInstance().getPatch("dialog_background"));

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = GameResource.getInstance().getDrawable("dialog_button");
        textButtonStyle.over = GameResource.getInstance().getDrawable("dialog_button_hover");
        textButtonStyle.down = GameResource.getInstance().getDrawable("dialog_button_pressed");
        textButtonStyle.font = textFont;

        textButtonStyle.fontColor = Color.BLACK;

        checkBoxStyle = new RightCheckBox.CheckBoxStyle();
        /*checkBoxStyle.checkboxOff = GameResource.getInstance().getDrawable("checkbox_checked");
        checkBoxStyle.checkboxOn = GameResource.getInstance().getDrawable("checkbox");
        checkBoxStyle.checkboxOver = GameResource.getInstance().getDrawable("checkbox_hover");*/

        checkBoxStyle.checkboxOff = GameResource.getInstance().getDrawable("checkbox");
        checkBoxStyle.checkboxOn = GameResource.getInstance().getDrawable("checkbox_checked");
        checkBoxStyle.checkboxOver = GameResource.getInstance().getDrawable("checkbox_hover");
        checkBoxStyle.font = new BitmapFont(Gdx.files.internal("resources/font/font_big.fnt"));
        checkBoxStyle.fontColor = Color.BLACK;

        label = new Label(null, new Label.LabelStyle(textFont, Color.BLACK));
    }


    public PixelizedDialog getDialog(String title, String content) {
        title = "\n" + title;

        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle);

        dialog.text(content, label.getStyle());

        dialog.getContentTable().add(label);
        return dialog;
    }

    public PixelizedDialog getComponetsSelect(String title) {
        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle);

        Table table = dialog.getButtonTable();
        table.padBottom(80).left();

        RightCheckBox zeroIndexCheck = new RightCheckBox(Components.deviceStrings[0], checkBoxStyle);

        table.add(zeroIndexCheck).left();
        zeroIndexCheck.getImageCell().expandX().right();
        //zeroIndexCheck.setDebug(true);

        table.setDebug(true);

        for(int i=1; i<Components.deviceStrings.length; i++) {
            RightCheckBox check = new RightCheckBox(Components.deviceStrings[i], checkBoxStyle);

            check.getImageCell().padLeft(10);

            if(i % 2 == 0) {
                table.row().padTop(20);
                table.add(check).left();
            } else {
                table.add(check).left().padLeft(20);
            }

            //check.setDebug(true);
            check.getImageCell().expandX().right();
        }

        TextButton.TextButtonStyle newStyle = new TextButton.TextButtonStyle(textButtonStyle);
        newStyle.fontColor = Color.PINK;

        dialog.button("위 재료가 필요하다고 구라치기 ㄱㄱ -->", true, newStyle);
        dialog.getButtonTable().getCells().get(dialog.getButtonTable().getCells().size - 1).padLeft(20);

        return dialog;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() { return textButtonStyle; }
}