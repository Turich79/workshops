package io.hexlet.states;

import io.hexlet.Character;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class NormalState implements State {
    private Character character;

    public NormalState(Character character) {
        System.out.println(character.getName() + " enters normal state");
        this.character = character;
        character.setCurrentAttackPower(character.getBaseAttackPower());
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

    }

    @Override
    public String returnStatus() {
        return "Normal";
    }

    @Override
    public void poison() {
        System.out.println(character.getName() + " exits normal state");
        character.setState(new PoisonedState(this.character));
    }

    @Override
    public void freeze() {
        System.out.println(character.getName() + " exits normal state");
        character.setState(new FrozenState(this.character));
    }

    @Override
    public void goBerserk() {
        System.out.println(character.getName() + " exits normal state");
        character.setState(new BerserkState(this.character));
    }

    @Override
    public void undoSpells() {

    }
}
