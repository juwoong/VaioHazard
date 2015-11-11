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

    enum DayOfWeek{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    private int money;
    private int reputaion;

    private int month;
    private int day;
    private DayOfWeek dayOfWeek;

    public static void newCenter() {
        center = new ServiceCenter();
        center.money = 10000;
        center.reputaion = 10;
        center.month = 1;
        center.day= 1;
        center.dayOfWeek = DayOfWeek.Monday;
    }

    /*public static ServiceCenter loadCenter() {

    }*/


}