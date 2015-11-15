
package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.Vaio;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.UUID;

/**
 * Created by YuTack on 2015-11-11.
 */

public class Customer extends VaioActor {
    private String name; //고객의 이름
    private final String uuid; //수리 맡긴 고객을 다시 등장시키기 위한 UUID - 중복 안 됨.
    private boolean isTakeOff = false;
    private String date;
    private int cost;
    private Vaio vaio;

    public Customer(String name, String image, int cols, int rows, String uuid) {
        this.name = name;
        setAnimation(image, cols, rows);
        this.uuid = uuid;
        isTakeOff = true;
    }

    public Customer(String name, String image, int cols, int rows){
        this.name = name;
        setAnimation(image, cols, rows);
        uuid = UUID.randomUUID().toString().replace("-","");
        /*고객의 호갱도, */
    }

    public Customer(String name) {
        this(name, "default.png", 1, 1);
    }

    public void setCost(int cost) { this.cost = cost; }
    public int getCost() { return cost; }

    public void setVaio(Vaio vaio) { this.vaio = vaio; }
    public Vaio getVaio() { return vaio; }

    /*public void save() {
        JSONObject json = new JSONObject();
        JSONObject size = new JSONObject();
        JSONObject takeOff = new JSONObject();

        size.put("cols", FRAME_COLS);
        size.put("rows", FRAME_ROWS);

        json.put("name", name);
        json.put("uuid", uuid);
        json.put("image", image);
        json.put("cost", cost);
        json.put("size", size);


        try{
            Writer writer = new FileWriter("resources/user/"+uuid+".txt");

            writer.write(json.toJSONString());
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }*/
}