package org.dimigo.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.vaiohazard.conversation.Conversation;
import org.dimigo.vaiohazard.conversation.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
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

    private Question convert(JSONObject obj) {
        String questionValue = (String) obj.get("question");
        String requiredAnswer = (String) obj.get("requiredAnswer");

        Question q = new Question(questionValue, requiredAnswer);

        if(obj.containsKey("answer") == true){
            JSONArray answer = (JSONArray) obj.get("answer");
            for(int i=0; i<answer.size(); i++) {
                q.insertQuestion(convert((JSONObject) answer.get(i)));
            }
        }


        System.out.println(q.toString());
        return q;
    }

    //"resources/conversation/question.json"
    public Conversation parse(String uri) {
        Conversation conv = new Conversation(stage);
        File file = new File(uri);
        byte[] bytes = new byte[(int)file.length()];

        try{
            InputStream is = new FileInputStream(file);
            is.read(bytes);

            String result = new String(bytes);
            JSONObject obj = (JSONObject) parser.parse(result);
            Question q = convert(obj);


            conv.setQuestion(q);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ParseException e) {
            e.printStackTrace();
        }

        conv.setStage();

        return conv;
    }
}
