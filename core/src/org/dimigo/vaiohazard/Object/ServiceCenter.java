package org.dimigo.vaiohazard.Object;

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

    private ServiceCenter() {
    }

    private int money;
    private int reputaion;
    private int doubt;
    private List<RepairOrder> orders = new ArrayList<RepairOrder>();

    private int month;
    private int day;
    private DayOfWeek dayOfWeek;
//
    public static void newCenter() {
        center = new ServiceCenter();
        center.money = 10000;
        center.reputaion = 10;
        center.month = 1;
        center.day= 1;
        center.dayOfWeek = DayOfWeek.MONDAY;

        center.doubt = 10;
    }

    /*public static ServiceCenter loadCenter() {

    }*/

    public void tomorrow() {

    }

    public void changeMoney(int money) {
        this.money = ((this.money-money)>=0 ? this.money-money : 0);
    }

    public void changeReputaion(int reputaion) {
        this.reputaion = ((this.reputaion-reputaion)>=0 ? this.reputaion-reputaion : 0);

    }

    public void changeDoubt(int doubt) {
        this.doubt = ((this.doubt-doubt)>=0 ? this.doubt-doubt : 0);
    }

    public int getMoney() {
        return money;
    }

    public int getReputaion() {
        return reputaion;
    }

    public int getDoubt() {
        return doubt;
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