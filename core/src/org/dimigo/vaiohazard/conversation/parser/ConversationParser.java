package org.dimigo.vaiohazard.conversation.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juwoong on 15. 11. 26..
 */
public class ConversationParser {
    public class ConversationFormat {
        public String question;
        public List<String> list;
    }

    private JSONParser parser = new JSONParser();

    protected List<ConversationFormat> get(String uri) {
        List<ConversationFormat> list = new ArrayList<ConversationFormat>();
        File file = new File("resources/conversation/" + uri + ".json");
        byte[] bytes = new byte[(int)file.length()];

        try{
            InputStream is = new FileInputStream(file);
            is.read(bytes);

            String result = new String(bytes);
            is.close();

            JSONObject obj = (JSONObject) parser.parse(result);
            JSONArray objList = (JSONArray) obj.get("list");

            for(int i=0; i<objList.size(); i++) {
                JSONObject value = (JSONObject) objList.get(i);
                ConversationFormat format = new ConversationFormat();
                if(value.containsKey("answers") == true) {
                }

                format.question = (String) value.get("question");
                list.add(format);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ParseException e) {
            e.printStackTrace();
        }

        return list;
    }
}
