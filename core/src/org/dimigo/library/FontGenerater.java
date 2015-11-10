package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juwoong on 15. 11. 9..
 */
public class FontGenerater {
    private FreeTypeFontGenerator generator;
    private List<BitmapFont> fonts;

    public FontGenerater() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("resources/font/font.ttf"));
        fonts = new ArrayList<BitmapFont>();
    }

    public void drawBitmapFont(Batch batch, int size, String str, Color color, int locX, int locY) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font;
        parameter.size = size;
        parameter.characters = str;
        parameter.color = color;

        font = generator.generateFont(parameter);

        font.draw(batch, str, locX, locY);
        fonts.add(font);
    }

    public BitmapFont getBitmapFont(int size, String str, Color color)
    {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font;

        parameter.size = size;
        parameter.characters = str;
        parameter.color = color;

        font = generator.generateFont(parameter);

        return font;
    }

    public void releaseAll() {
        for(BitmapFont font : fonts){
            font.dispose();
        }
        fonts.clear();
    }

    public BitmapFont getBitmapFont(int size, String str, Color color, int locX, int locY) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font;
        parameter.size = size;
        parameter.characters = str;
        parameter.color = color;

        font = generator.generateFont(parameter);
        return font;
    }
}
