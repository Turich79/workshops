package io.hexlet;

import io.hexlet.states.AlarmState;
import io.hexlet.states.BellState;
import io.hexlet.states.ClockState;
import io.hexlet.states.State;

public class AlarmClock implements State {
    private int hours;
    private int minutes;
    private int alarmHours;
    private int alarmMinutes;
    private boolean alarmOn;

    private State state;

    public AlarmClock() {
        this.hours = 12;
        this.minutes = 0;
        this.alarmHours = 6;
        this.alarmMinutes = 0;
        this.alarmOn = false;
        state = new ClockState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setAlarmOn() {
        this.alarmOn = !alarmOn;
    }

    public State getState() {
        return state;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getAlarmHours() {
        return alarmHours;
    }

    public int getAlarmMinutes() {
        return alarmMinutes;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }


    @Override
    public void clickMode() {
        state.clickMode();
    }

    @Override
    public void longClickMode() {
//        state.longClickMode();
        setAlarmOn();
    }

    @Override
    public void clickH() {
        state.clickH();
    }

    @Override
    public void clickM() {
        state.clickM();
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setAlarmHours(int alarmHours) {
        this.alarmHours = alarmHours;
    }

    public void setAlarmMinutes(int alarmMinutes) {
        this.alarmMinutes = alarmMinutes;
    }

    @Override
    public void tick() {
        state.tick();

    }

    public boolean isAlarmTime() {
        if (hours == alarmHours && minutes == alarmMinutes) {
            return true;
        } else {
            return false;
        }
    }

    public String getCurrentMode() {
        return state.toString();
    }

}
