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



    public Scheduler(int month, int day) {
        this.month = month;
        this.day = day;


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
