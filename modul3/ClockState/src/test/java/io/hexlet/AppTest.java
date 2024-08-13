package io.hexlet;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    void testDefaultState() {
        AlarmClock clock = new AlarmClock();

        assertThat(clock.getMinutes()).isEqualTo(0);
        assertThat(clock.getHours()).isEqualTo(12);
        assertThat(clock.getAlarmHours()).isEqualTo(6);
        assertThat(clock.getAlarmMinutes()).isEqualTo(0);
    }

    @Test
    void testChangeStateWhenClickMode() {
        AlarmClock clock = new AlarmClock();

        assertThat(clock.isAlarmOn()).isFalse();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");

        clock.clickMode();
        clock.tick();
        assertThat(clock.isAlarmOn()).isFalse();
        assertThat(clock.getCurrentMode()).isEqualTo("alarm");

        clock.clickMode();
        clock.tick();
        assertThat(clock.isAlarmOn()).isFalse();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");

        clock.longClickMode();
        clock.tick();
        assertThat(clock.isAlarmOn()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");

        clock.clickMode();
        clock.tick();
        assertThat(clock.isAlarmOn()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("alarm");

        clock.clickMode();
        clock.tick();
        assertThat(clock.isAlarmOn()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");

        clock.longClickMode();
        assertThat(clock.isAlarmOn()).isFalse();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testChangingHoursAndMinutes() {
        AlarmClock clock = new AlarmClock();

        clock.clickH();
        assertThat(clock.getMinutes()).isEqualTo(0);
        assertThat(clock.getHours()).isEqualTo(13);
        assertThat(clock.getAlarmMinutes()).isEqualTo(0);
        assertThat(clock.getAlarmHours()).isEqualTo(6);

        clock.clickM();
        assertThat(clock.getMinutes()).isEqualTo(1);
        assertThat(clock.getHours()).isEqualTo(13);
        assertThat(clock.getAlarmMinutes()).isEqualTo(0);
        assertThat(clock.getAlarmHours()).isEqualTo(6);

        clock.clickMode();

        clock.clickH();
        assertThat(clock.getMinutes()).isEqualTo(1);
        assertThat(clock.getHours()).isEqualTo(13);
        assertThat(clock.getAlarmMinutes()).isEqualTo(0);
        assertThat(clock.getAlarmHours()).isEqualTo(7);

        clock.clickM();
        assertThat(clock.getMinutes()).isEqualTo(1);
        assertThat(clock.getHours()).isEqualTo(13);
        assertThat(clock.getAlarmMinutes()).isEqualTo(1);
        assertThat(clock.getAlarmHours()).isEqualTo(7);

        for (int i = 0; i < 60; i++) {
            clock.clickM();
        }

        assertThat(clock.getAlarmMinutes()).isEqualTo(1);
        assertThat(clock.getAlarmHours()).isEqualTo(7);

        for (int i = 0; i < 17; i++) {
            clock.clickH();
        }

        assertThat(clock.getAlarmHours()).isEqualTo(0);
    }

    @Test
    void testNoBellingIfAlarmOff() {
        AlarmClock clock = new AlarmClock();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
        assertThat(clock.getMinutes()).isEqualTo(0);
        assertThat(clock.getHours()).isEqualTo(6);
        clock.tick();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testStartingBellIfAlarmOn() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("bell");
        assertThat(clock.getMinutes()).isEqualTo(0);
        assertThat(clock.getHours()).isEqualTo(6);
        clock.tick();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testStartingBellIfAlarmOn1() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("bell");
        clock.clickMode();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testStartingBellIfAlarmOn3() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();
        clock.clickMode();
        assertThat(clock.getCurrentMode()).isEqualTo("alarm");

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.isAlarmOn()).isTrue();

        assertThat(clock.getCurrentMode()).isEqualTo("bell");
        clock.clickMode();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testNoBellForAlarmModeIfAlarmOff() {
        AlarmClock clock = new AlarmClock();
        clock.clickMode();
        assertThat(clock.getCurrentMode()).isEqualTo("alarm");

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.isAlarmOn()).isFalse();
        assertThat(clock.getCurrentMode()).isEqualTo("alarm");

        clock.clickMode();
        clock.tick();
        assertThat(clock.getCurrentMode()).isEqualTo("clock");
    }

    @Test
    void testIncrementMinutesAfterAlarm() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        assertThat(clock.isAlarmTime()).isTrue();
        assertThat(clock.getCurrentMode()).isEqualTo("bell");

        clock.tick();

        assertThat(clock.getCurrentMode()).isEqualTo("clock");
        assertThat(clock.getMinutes()).isEqualTo(1);
    }
}