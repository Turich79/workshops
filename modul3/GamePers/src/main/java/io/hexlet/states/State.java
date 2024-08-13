package io.hexlet.states;

public interface State {
    int attack();
    void takeDamage(int damage);
    void update();
    void poison();
    void freeze();
    void goBerserk();
    void undoSpells();

    String returnStatus();
}
