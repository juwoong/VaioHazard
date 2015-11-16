
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
    private boolean isTakeOff = false; //이번 고객은 맡긴 물건을 찾으러 온 고객인가?
    private boolean isDoubt = false;
    private String date; //
    private int cost; //책정된 수리 비용
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
        //TODO: 고객의 의심도 설정 - 이 고객의 난이도와 연관되어 있음.
    }

    public Customer(String name) {
        this(name, "default.png", 1, 1);
    }

    public void setCost(int cost) { this.cost = cost; }
    public int getCost() { return cost; }

    public void setVaio(Vaio vaio) { this.vaio = vaio; }
    public Vaio getVaio() { return vaio; }

    //TODO: Vaio의 현재 수리 상태와, 전달받은 수리 상태를 비교한다. 물론 손님은 모르기 때문에 계산식을 이용해서 받아온다. 또한, 이 값과 의심도를 섞어 의심할 지 정한다.
    //현재 캐릭터의 상태가 Doubt 상태에 있는가?

    public boolean isDoubt(boolean[] list) {

        return isDoubt;
    }

    //TODO: 기록 저장 시 User Save 만들 것.
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