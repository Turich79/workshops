package io.hexlet;

import io.hexlet.states.NormalState;
import io.hexlet.states.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character implements State {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int baseAttackPower;
    private int currentAttackPower;
    private State state;

    public Character(String name, int maxHealth, int baseAttackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.baseAttackPower = baseAttackPower;
        this.currentAttackPower = baseAttackPower;
        state = new NormalState(this);
    }

    @Override
    public String returnStatus() {
        return "Character";
    }

    @Override
    public String toString() {
        return name + " (HP: " + currentHealth + "/" + maxHealth +
                ", AP: " + currentAttackPower + ", State: " + state.returnStatus() + ")";
    }

    @Override
    public int attack() {
        return state.attack();
    }

    @Override
    public void takeDamage(int damage) {
        state.takeDamage(damage);
    }

    @Override
    public void update() {
        state.update();
    }

    @Override
    public void poison() {
        state.poison();
    }

    @Override
    public void freeze() {
        state.freeze();
    }

    @Override
    public void goBerserk() {
        state.goBerserk();
    }

    @Override
    public void undoSpells() {
        state.undoSpells();
    }
}
