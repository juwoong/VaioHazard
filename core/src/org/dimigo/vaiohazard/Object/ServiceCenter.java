package org.dimigo.vaiohazard.Object;

import java.time.DayOfWeek;

/**
 * Created by YuTack on 2015-11-11.
 */

public class ServiceCenter {
    private static ServiceCenter center;

    public ServiceCenter getInstance() {
        return center;
    }

    private ServiceCenter() {

    }

    private int money;
    private int reputaion;
    private int doubt;

    private int month;
    private int day;
    private DayOfWeek dayOfWeek;

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