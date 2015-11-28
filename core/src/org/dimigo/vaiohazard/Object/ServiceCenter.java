package org.dimigo.vaiohazard.Object;

import org.dimigo.library.Rand;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Created by YuTack on 2015-11-11.
 */

public class ServiceCenter {
    private static ServiceCenter center = new ServiceCenter();
    public static ServiceCenter getInstance() {
        return center;
    }

    private ServiceCenter() {}

    private int money;
    private float reputaionPercent;
    private List<RepairOrder> orders = new ArrayList<RepairOrder>();

    private int month;
    private int day;
    private DayOfWeek dayOfWeek;

    //0 에서 1사이의 값
    private float inspectSkill = 0.5f;

    private Components components;
//
    public static void newCenter() {
        center = new ServiceCenter();
        center.money = 10000;
        center.reputaionPercent = 30 / 100;
        center.month = 1;
        center.day= 1;
        center.dayOfWeek = DayOfWeek.MONDAY;
    }

    /*public static ServiceCenter loadCenter() {

    }*/

    static public class InspectResult {
        public Map<VaioProblem.Trouble, VaioProblem.Critical> impairs;
        public int failCount;
    }

    //Fine인 문제를 착각할 정도로 멍청하진 않음, 그러나 스킬에 따라 있는 문제를 모르거나(가벼운 경우만) 심각성을 잘못 판단할 수 있음
    public InspectResult inspectVaio(Vaio vaio) {
        Map<VaioProblem.Trouble, VaioProblem.Critical> inspectResult = new HashMap<VaioProblem.Trouble, VaioProblem.Critical>();
        int failCount = 0;

        for (VaioProblem.Trouble realTrouble : vaio.getImpairs().keySet()) {
            if (vaio.getImpairs().get(realTrouble) == VaioProblem.Critical.Fine) {

                inspectResult.put(realTrouble, VaioProblem.Critical.Fine);

            } else if (vaio.getImpairs().get(realTrouble) == VaioProblem.Critical.Little) {

                if (Rand.get(inspectSkill)) inspectResult.put(realTrouble, VaioProblem.Critical.Little);
                else {
                    inspectResult.put(realTrouble, VaioProblem.Critical.Fine);
                    failCount++;
                }

            } else {

                if (Rand.get(inspectSkill)) {
                    inspectResult.put(realTrouble, vaio.getImpairs().get(realTrouble));
                } else {
                    Random rand = new Random();
                    inspectResult.put(realTrouble, VaioProblem.Critical.values()[2 + rand.nextInt(3)]);
                    failCount++;
                }

            }
        }

        InspectResult result = new InspectResult();
        result.impairs = inspectResult;
        result.failCount = failCount;
        return result;
    }


    public void addRepairOrder(RepairOrder order) {
        orders.add(order);
    }

    public int getMoney() {
        return money;
    }

    public float getReputaionPercent() {
        return reputaionPercent;
    }

    public float getInspectSkill() { return inspectSkill; }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}