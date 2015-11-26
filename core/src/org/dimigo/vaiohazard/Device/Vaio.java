package org.dimigo.vaiohazard.Device;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuTack on 2015-11-14.
 */
public class Vaio {
    private Map<VaioProblem.Trouble, VaioProblem.Critical> impairs;

    //내구성이 낮은 바이오는 수리해도 또 망가질 수 있습니다. 수리 확률에 영향
    private int durability = 100;

    boolean isInspected = false;

    public Vaio() {

    }

    public Vaio(HashMap<VaioProblem.Trouble, VaioProblem.Critical> impairs, int durability) {
        this.impairs = impairs;
        this.durability = durability;
    }

    public void setImpairs(Map<VaioProblem.Trouble, VaioProblem.Critical> impairs) {
        this.impairs = impairs;
    }

    public void setDurability(int durability) {
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

    public Map<VaioProblem.Trouble, VaioProblem.Critical> getImpairs() {
        return impairs;
    }

    public int getDurability() {
        return durability;
    }

    //TODO: Inspect 함수 완성
    public void inspect() {
        isInspected = true;
    }

    /*public Components repairResources() {
        Components require = new Components();
        for(Map.Entry<VaioProblem.Trouble, VaioProblem.Critical> entry : impairs.entrySet())
            require.addComponets(VaioProblem.requireComponents.get(entry.getKey()));

        return require;
    }*/

}