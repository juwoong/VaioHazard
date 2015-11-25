package org.dimigo.vaiohazard.Object;

import org.dimigo.vaiohazard.Device.Components;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

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

    public void changeMoney(int money) {
        this.money = ((this.money-money)>=0 ? this.money-money : 0);
    }

    public void changeReputaion(int reputaion) {
        this.reputaionPercent = ((this.reputaionPercent -reputaion)>=0 ? this.reputaionPercent -reputaion : 0);

    }

    public void negotiateRepairDevice(Customer target) {
        if(target.getPurpose() == Customer.REPAIR && target.getCumstomerState() == Customer.CumstomerState.readyToNegotiate) {
            //여기서 점원으로 자동 처리 또는 직접 처리


        }
    }

    public void negotiateRegainDevice(Customer target) {

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