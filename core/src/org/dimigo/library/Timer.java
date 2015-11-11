package org.dimigo.library;

/**
 * Created by YuTack on 2015-11-11.
 */
public class Timer {
    private float time = 0;
    private boolean isOn = false;
    private float alarmTime;

    public Timer() {

    }

    public Timer(float alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void timerStart() {
        isOn = true;
    }

    public void timerStart(float alarmTime) {
        this.alarmTime = alarmTime;
        isOn = true;
    }

    public void timerPause() {
        isOn = false;
    }

    public void timerReset() {
        isOn = false;
        time = 0;
    }

    public float getTime() {
        return time;
    }

    public float getAlarmTime() {
        return alarmTime;
    }

    public boolean update(float delta) {
        if(isOn) {
            time += delta;
            if(time >= alarmTime) {
                timerReset();
                return true;
            }
        }
        return false;
    }
}
