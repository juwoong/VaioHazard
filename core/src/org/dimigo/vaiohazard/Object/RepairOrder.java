package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.VaioProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuTack on 2015-11-14.
 */
//진단 후 고객과의 협의 이후 결과로 나옴
public class RepairOrder {
    private Customer orderer;
    private List<VaioProblem.Trouble> troublesToFix = new ArrayList<VaioProblem.Trouble>();

    private final int appointmentMonth;
    private final int appointmentDate;
    private final int appointmentMinutes;

    private final int reward;

    public RepairOrder(Customer orderer, int appointmentMonth, int appointmentDate, int appointmentMinutes, int reward) {
        this.orderer = orderer;
        this.appointmentMonth = appointmentMonth;
        this.appointmentDate = appointmentDate;
        this.appointmentMinutes = appointmentMinutes;
        this.reward = reward;
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

    public int getAppointmentMinutes() {
        return appointmentMinutes;
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
}
