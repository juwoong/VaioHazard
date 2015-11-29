package org.dimigo.vaiohazard.control;

import org.dimigo.library.NameGenerator;
import org.dimigo.vaiohazard.Object.Customer;
import org.dimigo.vaiohazard.Object.RepairOrder;
import org.dimigo.vaiohazard.Object.ServiceCenter;

import java.util.*;

/**
 * Created by juwoong on 15. 11. 14..
 */
public class Scheduler {
    //(시간, 사람) 제네레이터..
    private Map<Integer, String> todayCustomer = new HashMap<Integer, String>();
    private int month, day;
    private Random random;

    /////////
    boolean testFlag = false;
    ////////

    private List<RepairOrder> repairOrders;
    private List<Customer> customers;

    public Scheduler(int month, int day, List<RepairOrder> repairOrders, List<Customer> customers) {
        this.month = month;
        this.day = day;
        this.repairOrders = repairOrders;
        this.customers = customers;

        //INSERT characterName to Schedule
        int customerCount = 20;
        for(int i=0; i<customerCount; i++) {
            while(true) {
                int time = random.nextInt(540)+540;
                String name = NameGenerator.getInstance().getName();
                if(todayCustomer.containsKey(time) || todayCustomer.containsValue(name)) continue;

                todayCustomer.put(time, name);
                break;
            }
        }
    }

    public void update(float deltaTime) {
        /*for(RepairOrder order : repairOrders) {

        }*/
        if(testFlag == false) {
            customers.add(new Customer(NameGenerator.getInstance().getName(), ServiceCenter.getInstance())
            customers.add(new Customer(NameGenerator.getInstance().getName(), ServiceCenter.getInstance().getWaitingNumber())
        }

    }
}
