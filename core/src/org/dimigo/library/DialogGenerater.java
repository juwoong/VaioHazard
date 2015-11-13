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
    private BitmapFont font;
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

        font = new BitmapFont(Gdx.files.internal("resources/font/font_black.fnt"));

        windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        NinePatch background = skin.getPatch("Dialog_");
        windowStyle.background = new NinePatchDrawable(background);

        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("Button");
        style.over = skin.getDrawable("Button_hover");
        style.down = skin.getDrawable("Button_pressed");
        style.font = font;

        labelStyle = new Label.LabelStyle(font, Color.BLACK);
        label = new Label(null, labelStyle);
        label.setAlignment(Align.center);
    }

    public PixelizedDialog getDialog(String str) {
        str = "\n" + str;

        PixelizedDialog dialog = new PixelizedDialog(str, windowStyle);

        dialog.getContentTable().add(label);
        return dialog;
    }


    public TextButton.TextButtonStyle getStyle() { return style; }
}