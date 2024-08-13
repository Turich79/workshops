package io.hexlet.states;

import io.hexlet.Character;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class PoisonedState implements State {
    private Character character;

    @Override
    public String returnStatus() {
        return "Poisoned";
    }

    public PoisonedState(Character character) {
        System.out.println(character.getName() + " is poisoned");
        this.character = character;

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
    public void update() throws IllegalStateException {
        character.setCurrentAttackPower(character.getBaseAttackPower() / 2);
        if (character.getCurrentHealth() >5) {
            character.setCurrentHealth(character.getCurrentHealth() - 5);
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public void poison() {
        System.out.println("in class poison");
    }

    @Override
    public void freeze() {
        System.out.println(character.getName() + " is no longer poisoned");
        character.setState(new FrozenState(this.character));
    }

    @Override
    public void goBerserk() {
        System.out.println(character.getName() + " is no longer poisoned");
        character.setState(new BerserkState(this.character));
    }

    @Override
    public void undoSpells() {
        System.out.println(character.getName() + " is no longer poisoned");
        character.setState(new NormalState(this.character));
    }
}
