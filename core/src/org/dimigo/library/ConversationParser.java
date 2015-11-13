package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.vaiohazard.conversation.Conversation;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by juwoong on 15. 11. 13..
 */
public class ConversationParser {
    private JSONParser parser;
    Stage stage;

    public ConversationParser(Stage stage) {
        parser = new JSONParser();
        this.stage = stage;
    }

    public Conversation parse(String uri) {
        Conversation conv = new Conversation(stage);

        try(InputStream is = new FileInputStream("resources/conversation/question.json")){

        }catch(IOException e) {
            e.printStackTrace();
        }

        return conv;
    }
}
