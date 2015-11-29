package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.VaioProblem;

import java.util.*;

/**
 * Created by YuTack on 2015-11-14.
 */
//진단 후 고객과의 협의 이후 결과로 나옴
public class RepairOrder {
    private Customer orderer;
    private List<VaioProblem.Trouble> troublesToFix = new ArrayList<VaioProblem.Trouble>();

    private final int appointmentMonth;
    private final int appointmentDate;
    private final int reward;

    /*
    * LiquidDisplayTrouble,
        BootTrouble,
        PowerTrouble,
        LCDTrouble,
        FunctionTrouble,
        Cleaning,
        BatteryTrouble;*/

    Map<VaioProblem.Trouble, VaioProblem.Critical> detail;
    Map<VaioProblem.Trouble, Integer> costTable = new HashMap<VaioProblem.Trouble, Integer>(){{
        put(VaioProblem.Trouble.LiquidDisplayTrouble, 500000);
        put(VaioProblem.Trouble.BootTrouble, 25000);
        put(VaioProblem.Trouble.PowerTrouble, 300000);
        put(VaioProblem.Trouble.LCDTrouble, 700000);
        put(VaioProblem.Trouble.FunctionTrouble, 1100000);
        put(VaioProblem.Trouble.Cleaning, 20000);
        put(VaioProblem.Trouble.BatteryTrouble, 110000);
    }};

    Map<VaioProblem.Critical, Float> costValue = new HashMap<VaioProblem.Critical, Float>(){{
        put(VaioProblem.Critical.Fine, 0.0f);
        put(VaioProblem.Critical.Little, 0.2f);
        put(VaioProblem.Critical.Soso, 0.4f);
        put(VaioProblem.Critical.Serious, 0.6f);
        put(VaioProblem.Critical.Bad, 0.8f);
        put(VaioProblem.Critical.Died, 1.0f);
    }};

    public RepairOrder(Customer orderer, int appointmentMonth, int appointmentDate, int reward) {
        this.orderer = orderer;
        this.appointmentMonth = appointmentMonth;
        this.appointmentDate = appointmentDate;
        this.reward = reward;
    }

    private int calculateReward() {
        int cost = 0;

        //총 비용을 계산합니다. Problem 종류 중 퍼센트를 곱합니다.

        for(Map.Entry<VaioProblem.Trouble, VaioProblem.Critical> pair : detail.entrySet()) {
            cost += (int) ((double)costTable.get(pair.getKey())) * costValue.get(pair.getValue());
        }

        return cost;
    }

    public RepairOrder(Customer orderer, Map<VaioProblem.Trouble, VaioProblem.Critical> detail) {
        ServiceCenter center = ServiceCenter.getInstance();
        //10일 내로 찾아옵니다.
        int date = center.getDay() + (new Random()).nextInt(10)+1;
        int month = center.getMonth();

        //월 지나는 걸 처리합니다 ㅜㅜ
        appointmentDate = ((date%(ServiceCenter.monthlyDate[month-1]+1))+date/(ServiceCenter.monthlyDate[month-1]+1));
        appointmentMonth = ((month+date/(ServiceCenter.monthlyDate[month-1]+1))%13+(month+date/(ServiceCenter.monthlyDate[month-1]+1))/13);
        this.detail = detail;
        this.reward = calculateReward();

    }

    public int getReward() {
        return reward;
    }

    public Customer getOrderer() {
        return orderer;
    }

    public List<VaioProblem.Trouble> getTroublesToFix() {
        return troublesToFix;
    }

    public int getAppointmentMonth() {
        return appointmentMonth;
    }

    public int getAppointmentDate() {
        return appointmentDate;
    }

/*    public void addRepairOrder(Components.Component trouble) {
        list.add(trouble);
    }

    public void addRepairOrder(List<Components.Component> troubles) {
        list.addAll(troubles);
    }

    public void addRepairOrder(Components.Component[] troubles) {
        list.addAll(Arrays.asList(troubles));
    }*/

    public void addTrouble(VaioProblem.Trouble trouble) {
        troublesToFix.add(trouble);
    }

    public Map<VaioProblem.Trouble, VaioProblem.Critical> getDetail() {
        return detail;
    }
}
