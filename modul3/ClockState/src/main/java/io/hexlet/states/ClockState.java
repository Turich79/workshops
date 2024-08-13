package io.hexlet.states;

import io.hexlet.AlarmClock;
public class ClockState implements State {
    AlarmClock context;

    public ClockState(AlarmClock context) {
        this.context = context;
    }

    @Override
    public void clickMode() {
        context.setState(new AlarmState(this.context));
    }

    @Override
    public void longClickMode() {
    }

    @Override
    public void clickH() {
        var sHours = context.getHours() + 1;
        if (sHours == 24) {
            sHours = 0;
        }
        context.setHours(sHours);
    }

    @Override
    public void clickM() {
        var sMinutes = context.getMinutes() + 1;
        if (sMinutes == 60) {
            sMinutes = 0;
        }
        context.setMinutes(sMinutes);
    }

    @Override
    public void tick() {
        var sHours = context.getHours();
        var sMinutes = context.getMinutes() + 1;
        if (sMinutes == 60) {
            sMinutes = 0;
            sHours++;
        }
        if (sHours == 24) {
            sHours = 0;
        }
        context.setMinutes(sMinutes);
        context.setHours(sHours);

        if (context.isAlarmTime() && context.isAlarmOn()) {
            context.setState(new BellState(this.context));
        }
    }

    @Override
    public String toString() {
        return "clock";
    }
}