package io.hexlet.states;

import io.hexlet.Character;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class FrozenState implements State {
    private Character character;

    public FrozenState(Character character) {
        System.out.println(character.getName() + " is frozen");
        this.character = character;
        character.setCurrentAttackPower(0);
    }

    @Override
    public int attack() {
        System.out.println(character.getName() + " attacks for " + character.getCurrentAttackPower() + " damage");
        return character.getCurrentAttackPower();
    }

    @Override
    public void takeDamage(int damage) throws IllegalStateException{
        if (damage < character.getCurrentHealth()) {
            character.setCurrentHealth(character.getCurrentHealth() - damage);
        } else {
            throw new IllegalStateException("нельзя убить больше чем жизнь игрока");
        }
    }

    @Override
    public void update() {
        character.setCurrentAttackPower(0);
    }

    @Override
    public void poison() {
        System.out.println(character.getName() +" is no longer frozen");
        character.setState(new PoisonedState(this.character));
    }

    @Override
    public void freeze() {

    }

    @Override
    public void goBerserk() {
        System.out.println(character.getName() +" is no longer frozen");
        character.setState(new BerserkState(this.character));
    }

    @Override
    public void undoSpells() {
        System.out.println(character.getName() +" is no longer frozen");
        character.setState(new NormalState(this.character));
    }

    @Override
    public String returnStatus() {
        return "Frozen";
    }
}
