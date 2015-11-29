package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.StringBuilder;
import net.dermetfan.gdx.physics.box2d.PositionController;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.GameResource;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.Object.ServiceCenter;
import org.dimigo.vaiohazard.conversation.Conversation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private static Label.LabelStyle bigLabelStyle;
    private Conversation conv;
    private boolean isInspected = false;


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
        bigLabelStyle = new Label.LabelStyle(titleFont, Color.BLACK);
    }

    public DialogGenerator(Conversation conv) {
        this.conv = conv;
    }

    public PixelizedDialog getDialog(String title, String content) {

        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle, conv);

        dialog.text(content, labelStyle);
        return dialog;
    }

    private ServiceCenter.InspectResult inspectResult;
    private VaioProblem.Trouble trouble;

    public PixelizedDialog getInspectLoading(String title,  ServiceCenter.InspectResult inspectResults) {
        PixelizedDialog dialog = new PixelizedDialog(title, windowStyle, conv);

        inspectResult = inspectResults;
        //Label inspect = new Label("조사중", bigLabelStyle);

        Label inspect = new Label("조사 중", bigLabelStyle){
            @Override
            public void act(float deltaTime) {
                super.act(deltaTime);
                if(isInspected == false) {
                    SequenceAction seq = new SequenceAction();

                    for(VaioProblem.Trouble troubled : inspectResult.impairs.keySet()) {
                        trouble = troubled;
                        SequenceAction fadeInOutStep = new SequenceAction();

                        int blinkNum = (new Random()).nextInt(5) + 2;
                        float inOutDuration = 1.4f;

                        for(int i=0; i<=blinkNum; i++) {
                            fadeInOutStep.addAction(Actions.fadeIn(inOutDuration));

                            final int index = i;

                            if(i==blinkNum  || i==blinkNum-1) {
                                fadeInOutStep.addAction(new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        String str = trouble.name() + "이/가 " + inspectResult.impairs.get(trouble).name() + "한 Feeling이군!";
                                        setText(str);

                                        return true;
                                    }
                                });
                            } else {
                                fadeInOutStep.addAction(new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        StringBuilder builder = new StringBuilder();
                                        builder.append("조사 중 ");
                                        for(int j=0; j<index; j++) {
                                            builder.append(". ");
                                        }
                                        setText(builder);
                                        return true;
                                    }
                                });
                            }
                            fadeInOutStep.addAction(Actions.fadeOut(inOutDuration));
                        }
                        seq.addAction(fadeInOutStep);
                    }
                    addAction(seq);
                    isInspected = true;
                }
            }
        };

        //원래다이얼로그에서 컨텐트 테이블은 왼쪽부터 글쓰는 곳이라 padLeft들어가 있는데 이건 아니니까 오른쪽으로 옮기깅!
        dialog.getContentTable().add(inspect).expandX().center().padRight(50).padTop(40);



        dialog.button("볼장은 다봤깅!", true, textButtonStyle);
        return dialog;
    }

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
            if(Rand.get(ServiceCenter.getInstance().getInspectSkill()))
                contentTable.add(new Label(inspectResult.impairs.get(trouble).name(), labelStyle));
            else
                contentTable.add(new Label("아몰랑:)", labelStyle));

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