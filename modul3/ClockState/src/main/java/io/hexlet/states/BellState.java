package io.hexlet.states;

import io.hexlet.AlarmClock;

public class BellState implements State {
    AlarmClock context;

    public BellState(AlarmClock context) {
        this.context = context;
    }

    @Override
    public void clickMode() {
        context.setState(new ClockState(this.context));
    }

    @Override
    public void longClickMode() {

    }

    @Override
    public void clickH() {

    }

    @Override
    public void clickM() {

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

        context.setState(new ClockState(this.context));
    }

    @Override
    public String toString() {
        return "bell";
    }
}