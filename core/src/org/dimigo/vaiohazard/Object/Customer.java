
package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.Trouble;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by YuTack on 2015-11-11.
 */

public class Customer extends VaioActor {
    private String name; //고객의 이름
    private RepairOrder order;
    private final String uuid; //수리 맡긴 고객을 다시 등장시키기 위한 UUID - 중복 안 됨.
    private boolean isTakeOff = false; //이번 고객은 맡긴 물건을 찾으러 온 고객인가?
    private boolean isCaught = false;
    private String date; //
    private int cost; //책정된 수리 비용
    private Vaio vaio;
    Level level;
    private int doubtLevel = 0;


    //고객의 난이도. 의심도가 달라집니다
    enum Level {
        idiot, //의심도 0~30
        stupid, //의심도 30~45
        normal, //의심도 45~60
        sharp, //의심도 60~75
        brilliant; //의심도 75~90

        private static final List<Level> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Level getLevel() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
        //public static List<Level> getList() { return VALUES; }
    }

    public Customer(String name, String image, int cols, int rows, String uuid) {
        this.name = name;
        setAnimation(image, cols, rows);
        this.uuid = uuid;
        isTakeOff = true;
    }

    public Customer(String name, String image, int cols, int rows) {
        this.name = name;
        setAnimation(image, cols, rows);
        uuid = UUID.randomUUID().toString().replace("-", "");
        //TODO: 고객의 의심도 설정 - 이 고객의 난이도와 연관되어 있음.

        level = Level.getLevel();

        switch(level) {
            case idiot:
                doubtLevel = (new Random()).nextInt(30);
                break;
            case stupid:
                doubtLevel = (new Random()).nextInt(15)+30;
                break;
            case normal:
                doubtLevel = (new Random()).nextInt(15)+45;
                break;
            case sharp:
                doubtLevel = (new Random()).nextInt(15)+60;
                break;
            case brilliant:
                doubtLevel = (new Random()).nextInt(15)+75;
        }

    }

    public Customer(String name) {
        this(name, "default.png", 1, 1);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setVaio(Vaio vaio) {
        this.vaio = vaio;
    }

    public Vaio getVaio() {
        return vaio;
    }

    public void setRepairOrder(RepairOrder order) { this.order = order; }

    //TODO: Vaio의 현재 수리 상태와, 전달받은 수리 상태를 비교한다. 물론 손님은 모르기 때문에 계산식을 이용해서 받아온다. 또한, 이 값과 의심도를 섞어 의심할 지 정한다.
    //현재 캐릭터의 상태가 Doubt 상태에 있는가?

    public boolean isCaught(Map<VaioProblem.Trouble, VaioProblem.Critical> map) {
        //실제 고장난 리스트
        Map<Components.Component, VaioProblem.Critical> crash = vaio.getImpairs();
        boolean[] crashAbleComponents = VaioProblem.causeAbleReason.get(vaio.getTrouble());
        //고치겠다고 말한 리스트
        List<Components.Component> list = order.getRepairOrder();
        Map<Level, Integer> degree = new HashMap<Level, Integer>(){{
            put(Level.idiot, 0);
            put(Level.stupid, 1);
            put(Level.normal, 2);
            put(Level.sharp, 3);
            put(Level.brilliant, 4);
        }};

        /*
        TODO: 의심 알고리즘
        일단 실제 고장났으면 예외처리함.
         */

        int sum = 0;
        for(int i=0; i<list.size(); i++) {
            if(crash.containsKey(list.get(i))) continue;
            if(crashAbleComponents[i]) doubtLevel += 5;
            doubtLevel += (new Random()).nextInt(degree.get(level));
        }

        if(doubtLevel >= 100) isCaught = true;

        return isCaught;
    }

    //TODO: 기록 저장 시 User Save 만들 것.
}