package io.hexlet.states;

import io.hexlet.AlarmClock;
public class AlarmState implements State {
    AlarmClock context;

    public AlarmState(AlarmClock context) {
        this.context = context;
    }

    @Override
    public void clickMode() {
        context.setState(new ClockState(this.context));
    }

    @Override
    public void longClickMode() {
//        context.setAlarmOn();
    }

    @Override
    public void clickH() {
        var sHours = context.getAlarmHours() + 1;
        if (sHours == 24) {
            sHours = 0;
        }
        context.setAlarmHours(sHours);
    }

    @Override
    public void clickM() {
        var sMinutes = context.getAlarmMinutes() + 1;
        if (sMinutes == 60) {
            sMinutes = 0;
        }
        context.setAlarmMinutes(sMinutes);
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
        return "alarm";
    }
}
