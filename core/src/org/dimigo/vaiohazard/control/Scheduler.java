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
    private static final int MIN_CLASS_INTERVAL = 10;
    private static final int MAX_CLASS_INTERVAL = 100;
    private static final int OPENING_TIME = 540;
    private static final int CLOSING_TIME = 1080;

    private int month, day;
    private Random random;

    private Map<Integer, Customer> todayCustomers;

    public Scheduler(int month, int day, List<RepairOrder> repairOrders) {
        this.month = month;
        this.day = day;

        todayCustomers = new HashMap<Integer, Customer>();

        for(RepairOrder order : repairOrders) {
            if(order.getAppointmentDate() == day && order.getAppointmentMonth() == month) {
                todayCustomers.put(order.getAppointmentMinutes(), order.getOrderer());
            }
        }

        random = new Random();
        int time = OPENING_TIME;
        while (true) {
            int classInterval = random.nextInt(MAX_CLASS_INTERVAL - MIN_CLASS_INTERVAL) + MIN_CLASS_INTERVAL;
            time += classInterval;
            if(time >= CLOSING_TIME) break;
            else if(todayCustomers.containsKey(time) == false){
                todayCustomers.put(time, new Customer(NameGenerator.getInstance().getName(), "mario.png", 4, 1));
            }
        }
    }

    //고객이 동시에 들어오면 문제가 발생함
    public void update(float deltaTime) {
        int upTime = -1;
        for(int time : todayCustomers.keySet()) {
            if(ServiceCenter.getInstance().getMinutes() == time) {
                ServiceCenter.getInstance().addCustomer(todayCustomers.get(time));
                upTime = time;
                break;
            }
        }

        todayCustomers.remove(upTime);


    }
}
