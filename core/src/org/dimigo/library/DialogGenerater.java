package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import org.dimigo.vaiohazard.Object.PixelizedDialog;

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
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resources/dialog.atlas"));
        Skin skin = new Skin();
        skin.addRegions(atlas);

        font = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        NinePatch background = skin.getPatch("background");
        windowStyle.background = new NinePatchDrawable(background);

        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("touched-button");
        style.font = font;

        labelStyle = new Label.LabelStyle(font, Color.WHITE);
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
    public PixelizedDialog getDialog2(String str) {
        FontGenerater generater = new FontGenerater();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resources/dialog.atlas"));
        Skin skin = new Skin();
        skin.addRegions(atlas);

        BitmapFont font = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        font.setColor(1f, 1f, 1f, 1f);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        NinePatch background = skin.getPatch("background");
        windowStyle.background = new NinePatchDrawable(background);


        PixelizedDialog dialog = new PixelizedDialog(str, windowStyle);
        return dialog;
    }
}
