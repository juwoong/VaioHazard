package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import java.util.List;

/**
 * Created by juwoong on 15. 11. 10..
 */
public class DialogGenerater {
    private FontGenerater generater;
    private BitmapFont textFont, titleFont;
    private Skin skin;
    private Window.WindowStyle windowStyle;
    private TextButton.TextButtonStyle style;
    private Label.LabelStyle labelStyle;
    private Label label;

    public DialogGenerater() {
        generater = new FontGenerater();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resources/Ui/Dialog/Dialog.atlas"));
        Skin skin = new Skin();
        skin.addRegions(atlas);

        textFont = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("resources/font/font_big.fnt"));

        windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = titleFont;

        //이거 동적으로
        windowStyle.titleFontColor = Color.NAVY;
        //

        NinePatch background = skin.getPatch("Dialog_");
        windowStyle.background = new NinePatchDrawable(background);

        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("Button");
        style.over = skin.getDrawable("Button_hover");
        style.down = skin.getDrawable("Button_pressed");
        style.font = textFont;
        style.fontColor = Color.BLACK;

        labelStyle = new Label.LabelStyle(textFont, Color.BLACK);
        label = new Label(null, labelStyle);
        label.setAlignment(Align.center);

        //여기 뭔가 코드가 사라졌는데 뭔지 모르겠다 ㅈㅅ;
    }

    public PixelizedDialog getDialog(String str) {
        str = "\n" + str;

        PixelizedDialog dialog = new PixelizedDialog(str, windowStyle);

        dialog.getContentTable().add(label);
        return dialog;
    }


    public TextButton.TextButtonStyle getStyle() { return style; }
}