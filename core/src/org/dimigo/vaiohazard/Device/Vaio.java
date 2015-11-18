package org.dimigo.vaiohazard.Device;

import org.dimigo.vaiohazard.Trouble;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by YuTack on 2015-11-14.
 */
public class Vaio {
    //고쳐야 할 항목
    private Map<Components.Component, VaioProblem.Critical> impairs;

    //내구성이 낮은 바이오는 수리해도 또 망가질 수 있습니다. 수리 확률에 영향
    private int durability = 100;
    private VaioProblem.Trouble trouble;
    boolean isInspected = false;

    public Vaio() {
        //오류 원인, 실제 고장난 부품 등을 자동으로 정합니다.
        //아직까지 원인은 하나입니다.
        //TODO: 다수의 원인 추가 가능하게 하기
        trouble = VaioProblem.Trouble.getTrouble();
        boolean[] causeAble = VaioProblem.causeAbleReason.get(trouble);
        Random random = new Random();

        //고장난 원인들을 정합니다.
        for(int i=0; i<causeAble.length; i++) {
            if(causeAble[i] == false) continue;
            if(random.nextBoolean()) {
                impairs.put(Components.Component.getList().get(i), VaioProblem.Critical.getCritical());
            }
        }

        int temp = random.nextInt(5);
        //내구성을 책정합니다.
        if(temp == 0) durability = random.nextInt(30);
        else if(temp == 4) durability = random.nextInt(40)+60;
        else durability = random.nextInt(30)+30;

        //실제 고장난 부품들을 정합니다.

    }

    public Vaio(HashMap<Components.Component, VaioProblem.Critical> impairs, int durability) {
        this.impairs = impairs;
        this.durability = durability;
    }

    @Override
    public String toString() {
        if (isInspected) {
            return impairs.toString() + "현재 내구성 : " + durability;
        } else {
            return "파손 정도를 알 수 없는 Vaio 이다";
        }
    }


    public Map<Components.Component, VaioProblem.Critical> getImpairs() {
        return impairs;
    }


    public int getDurability() {
        return durability;
    }

    //나중에 기획이 끝나면 코드를 늘립니다.
    public void inspect() {
        isInspected = true;
    }

    public VaioProblem.Trouble getTrouble() { return trouble; }
}
