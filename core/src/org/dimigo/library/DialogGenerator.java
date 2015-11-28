package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.GameResource;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.Object.ServiceCenter;
import org.dimigo.vaiohazard.conversation.Conversation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class DialogGenerator {
    private static BitmapFont textFont, titleFont;
    private static Window.WindowStyle windowStyle;
    private static TextButton.TextButtonStyle textButtonStyle;
    private static RightCheckBox.CheckBoxStyle checkBoxStyle;
    private static SelectBox.SelectBoxStyle selectBoxStyle;
    private static Label.LabelStyle labelStyle;
    private Conversation conv;

    static {
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

        checkBoxStyle.checkboxOff = GameResource.getInstance().getDrawable("checkbox");
        checkBoxStyle.checkboxOn = GameResource.getInstance().getDrawable("checkbox_checked");
        checkBoxStyle.checkboxOver = GameResource.getInstance().getDrawable("checkbox_hover");
        checkBoxStyle.font = new BitmapFont(Gdx.files.internal("resources/font/font_big.fnt"));
        checkBoxStyle.fontColor = Color.BLACK;

        selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.background = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.backgroundOpen = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.backgroundOver = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.listStyle = new List.ListStyle(
                new BitmapFont(Gdx.files.internal("resources/font/font.fnt")),
                Color.RED, Color.BLACK, GameResource.getInstance().getDrawable("dialog_button_hover"));
        selectBoxStyle.scrollStyle = new ScrollPane.ScrollPaneStyle();
        selectBoxStyle.scrollStyle.background = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.scrollStyle.corner = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.scrollStyle.hScroll = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.scrollStyle.hScrollKnob = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.scrollStyle.vScroll = GameResource.getInstance().getDrawable("LightGreen");
        selectBoxStyle.scrollStyle.vScrollKnob = GameResource.getInstance().getDrawable("LightGreen");

        selectBoxStyle.font = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        selectBoxStyle.fontColor = Color.BLACK;

        labelStyle = new Label.LabelStyle(textFont, Color.BLACK);
    }

    public DialogGenerator(Conversation conv) {
        this.conv = conv;
    }

    public PixelizedDialog getDialog(String title, String content) {
        title = "\n" + title;

        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle, conv);

        dialog.getTitleTable().padBottom(25);

        dialog.text(content, labelStyle);
        return dialog;
    }

    /*public PixelizedDialog getInspectLoading(String title,  ServiceCenter.InspectResult inspectResult) {

    }*/

    public PixelizedDialog getImpairSelect(String title, ServiceCenter.InspectResult inspectResult) {
        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle, conv);

        Table contentTable = dialog.getContentTable();
        contentTable.top().padTop(77);

        Map<VaioProblem.Trouble, VaioProblem.Critical> selectResult = new HashMap<VaioProblem.Trouble, VaioProblem.Critical>();

        ImpairSelector selector = new ImpairSelector(selectBoxStyle);

        contentTable.add(new Actor());
        for(String troubleString : VaioProblem.TroubleStrings) {
            contentTable.add(new Label(troubleString, labelStyle));
        }

        contentTable.row().padTop(12);

        contentTable.add(new Label("조사 결과 :", labelStyle));
        for(VaioProblem.Trouble trouble : VaioProblem.Trouble.getList()) {
            contentTable.add(new Label(inspectResult.impairs.get(trouble).name(), labelStyle));
        }

        contentTable.row().padTop(12);

        contentTable.add(new Label("구라 치기 :", labelStyle));
        for(Object selectBox : selector.getSelectBoxes()) {
            if (selectBox instanceof SelectBox) {
                contentTable.add((SelectBox)selectBox).maxWidth(80).maxHeight(20).width(75).padLeft(5).height(20);
            }
        }

        dialog.button("이걸로 구라치기 ->", selector, textButtonStyle);

        return dialog;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() { return textButtonStyle; }

    public SelectBox.SelectBoxStyle getSelectBoxStyle() { return selectBoxStyle; }

    /*public PixelizedDialog getComponetsSelect(String title) {
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
    }*/
}