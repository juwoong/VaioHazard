package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by juwoong on 15. 11. 9..
 */
public class FontGenerater {
    private FreeTypeFontGenerator generator;

    public FontGenerater() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("resources/font/font.ttf"));
    }

    public BitmapFont getBitmapFont(int size, String str, Color color) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font;
        parameter.size = size;
        parameter.characters = str;
        parameter.color = color;

        font = generator.generateFont(parameter);
        //generator.dispose();

        return font;
    }
}
