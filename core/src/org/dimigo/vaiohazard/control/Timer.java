package org.dimigo.vaiohazard.control;

/**
 * Created by juwoong on 15. 11. 29..
 */
public class Timer extends Thread{
    private static Timer ourInstance = new Timer();

    public static Timer getInstance() {
        return ourInstance;
    }

    private Timer() {
    }

    @Override
    public void run() {

    }
}
