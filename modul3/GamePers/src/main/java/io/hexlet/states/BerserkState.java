package io.hexlet.states;

import io.hexlet.Character;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class BerserkState implements State {
    private Character character;

    public BerserkState(Character character) {
        System.out.println(character.getName() + " goes berserk");
        this.character = character;
        character.setCurrentAttackPower(character.getBaseAttackPower() * 2);
    }

    @Override
    public int attack() throws IllegalStateException {
        System.out.println(character.getName() + " attacks for " + character.getCurrentAttackPower() + " damage");
        if (character.getCurrentHealth() > character.getBaseAttackPower() / 2) {
            character.setCurrentHealth(character.getCurrentHealth() - character.getBaseAttackPower() / 2);
        } else {
            throw new IllegalStateException();
        }
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
        character.setCurrentAttackPower(character.getBaseAttackPower() * 2);
    }

    @Override
    public void poison() {
        System.out.println(character.getName() + " calms down");
        character.setState(new PoisonedState(this.character));
    }

    @Override
    public void freeze() {
        System.out.println(character.getName() + " calms down");
        character.setState(new FrozenState(this.character));
    }

    @Override
    public void goBerserk() {

    }

    @Override
    public void undoSpells() {
        System.out.println(character.getName() + " calms down");
        character.setState(new NormalState(this.character));
    }

    @Override
    public String returnStatus() {
        return "Berserk";
    }
}
