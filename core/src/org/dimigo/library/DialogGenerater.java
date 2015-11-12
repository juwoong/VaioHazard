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
    public DialogGenerater() {
    }

    public PixelizedDialog getDialog(String str) {


        FontGenerater generater = new FontGenerater();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resources/dialog.atlas"));
        Skin skin = new Skin();
        skin.addRegions(atlas);

        BitmapFont font = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        NinePatch background = skin.getPatch("background");
        windowStyle.background = new NinePatchDrawable(background);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("touched-button");
        style.font = font;

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label label = new Label(null, labelStyle);
        label.setAlignment(Align.center);

        PixelizedDialog dialog = new PixelizedDialog(str, windowStyle);

        dialog.button("Yes", "Exit", style);
        dialog.button("No", "Not yet", style);
        dialog.button("Maybe..?", "maybe", style);
        dialog.getContentTable().add(label);
        return dialog;
    }

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
