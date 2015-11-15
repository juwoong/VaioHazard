package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.Vaio;

/**
 * Created by YuTack on 2015-11-14.
 */
//진단 후 고객과의 협의 이후 결과로 나옴
public class RepairOrder {
    //or id
    Customer orderer;

    int appointmentMonth;
    int appointmentDate;

    //or id, 창고를 만들어 거기에 넣고 여기엔 id만 저장하던가 함
    int reward;

    public RepairOrder(Customer orderer, int appointmentMonth, int appointmentDate, int reward) {
        this.orderer = orderer;
        this.appointmentMonth = appointmentMonth;
        this.appointmentDate = appointmentDate;
        this.reward = reward;
    }
}
