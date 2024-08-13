package io.hexlet;

public class Main {
    public static void main(String[] args) {
        var character = new Character("Hero", 100, 20);
        // => Hero enters normal state
        System.out.println(character); // => Hero (HP: 100/100, AP: 20, State: Normal)

        character.poison();
        // => Hero exits normal state
        // => Hero is poisoned
        character.update();
        System.out.println(character); // => Hero (HP: 95/100, AP: 10, State: Poisoned)

        character.freeze();
        // => Hero is no longer poisoned
        // => Hero is frozen
        character.attack(); // 0
        System.out.println(character); // => Hero (HP: 95/100, AP: 0, State: Frozen)

        character.goBerserk();
        // => Hero is no longer frozen
        // => Hero goes berserk
        character.attack(); // 40
        System.out.println(character); // => Hero (HP: 85/100, AP: 40, State: Berserk)

        character.undoSpells();
        // => Hero calms down
        // => enters normal state
        System.out.println(character); // => Hero (HP: 85/100, AP: 20, State: Normal)

        // Здоровье персонажа не может уходить в минус
        character.takeDamage(90); // IllegalStateException
    }
}