package org.dimigo.vaiohazard.control;

import org.dimigo.library.NameGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by juwoong on 15. 11. 14..
 */
public class Scheduler {
    //(시간, 사람) 제네레이터..
    private Map<Integer, String> map = new HashMap<Integer, String>();
    private int month, day;
    private JSONParser parser;
    private Random random;

    private void textReader() throws IOException, org.json.simple.parser.ParseException{
        File directory = new File("resources/user/");
        File[] files = directory.listFiles();
        parser = new JSONParser();
        random = new Random();

        for(File file : files) {
            //현재 날짜를 비교한다.
            byte[] bytes = new byte[(int)file.length()];

            InputStream is = new FileInputStream(file);
            is.read(bytes);

            String result = new String(bytes);
            JSONObject obj = (JSONObject) parser.parse(result);
            JSONObject takeOff = (JSONObject) obj.get("takeOff");

            int month = (int) takeOff.get("month");
            int day = (int) takeOff.get("day");

            if(this.day == day && this.month == month) {
                //put UUID to map.
                while(true) {
                    int time = random.nextInt(540)+540;

                    if(map.containsKey(time)) continue;

                    map.put(time, file.getName());
                    break;
                }

            }

        }
    }

    public Scheduler(int month, int day) {
        this.month = month;
        this.day = day;

        try {
            textReader();
        }catch(IOException e){
            e.printStackTrace();
        }catch(org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        //INSERT characterName to Schedule
        int customerCount = 20;
        for(int i=0; i<customerCount; i++) {
            while(true) {
                int time = random.nextInt(540)+540;
                String name = NameGenerator.getInstance().getName();
                if(map.containsKey(time) || map.containsValue(name)) continue;

                map.put(time, name);
                break;
            }
        }
    }
}
