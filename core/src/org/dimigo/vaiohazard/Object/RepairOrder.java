package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.VaioProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuTack on 2015-11-14.
 */
//진단 후 고객과의 협의 이후 결과로 나옴
public class RepairOrder {
    Customer orderer;
    List<VaioProblem.Trouble> troublesToFix = new ArrayList<VaioProblem.Trouble>();

    int appointmentMonth;
    int appointmentDate;

    int reward;

    public RepairOrder(Customer orderer, int appointmentMonth, int appointmentDate, int reward) {
        this.orderer = orderer;
        this.appointmentMonth = appointmentMonth;
        this.appointmentDate = appointmentDate;
        this.reward = reward;
    }

    public void addTrouble(VaioProblem.Trouble trouble) {
        troublesToFix.add(trouble);
    }
}
