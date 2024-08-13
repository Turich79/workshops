package io.hexlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AppTest {

    private Character character;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setCharacter() {
        character = new Character("Hero", 100, 20);
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void testDefaultState() {
        assertThat(character.getName()).isEqualTo("Hero");
        assertThat(character.getMaxHealth()).isEqualTo(100);
        assertThat(character.getCurrentHealth()).isEqualTo(100);
        assertThat(character.getBaseAttackPower()).isEqualTo(20);
        assertThat(character.getCurrentAttackPower()).isEqualTo(20);

        var actualDamage = character.attack();
        assertThat(actualDamage).isEqualTo(character.getBaseAttackPower());

        final var actualOutput = out.toString().trim();
        assertThat(actualOutput).contains("Hero attacks for 20 damage");

        var expectedRepresentation = "Hero (HP: 100/100, AP: 20, State: Normal)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation);
    }

    @Test
    void testPoisonedState() {

        character.poison();
        assertThat(character.getCurrentHealth()).isEqualTo(character.getMaxHealth());

        character.update();
        assertThat(character.getCurrentHealth())
                .isEqualTo(character.getMaxHealth() - 5);
        assertThat(character.getCurrentAttackPower())
                .isEqualTo(character.getBaseAttackPower() / 2);

        var actualDamage = character.attack();
        assertThat(actualDamage).isEqualTo(character.getBaseAttackPower() / 2);

        final var actualOutput = out.toString().trim();
        assertThat(actualOutput)
                .contains("Hero exits normal state")
                .contains("Hero is poisoned")
                .contains("Hero attacks for 10 damage");

        var expectedRepresentation = "Hero (HP: 95/100, AP: 10, State: Poisoned)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation);
    }

    @Test
    void testFrozenState() {
        character.freeze();
        character.update();

        assertThat(character.getCurrentHealth()).isEqualTo(character.getMaxHealth());
        assertThat(character.getCurrentAttackPower()).isEqualTo(0);
        assertThat(character.attack()).isEqualTo(0);

        final var actualOutput = out.toString().trim();
        assertThat(actualOutput)
                .contains("Hero exits normal state")
                .contains("Hero is frozen")
                .contains("Hero attacks for 0 damage");

        var expectedRepresentation = "Hero (HP: 100/100, AP: 0, State: Frozen)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation);
    }

    @Test
    void testBerserkState() {
        character.goBerserk();
        character.update();

        assertThat(character.getCurrentHealth()).isEqualTo(character.getMaxHealth());

        var actualDamage = character.attack();
        assertThat(actualDamage).isEqualTo(character.getBaseAttackPower() * 2);

        var selfDamage = character.getBaseAttackPower() / 2;
        assertThat(character.getCurrentHealth())
                .isEqualTo(character.getMaxHealth() - selfDamage);

        final var actualOutput = out.toString().trim();
        assertThat(actualOutput)
                .contains("Hero exits normal state")
                .contains("Hero goes berserk")
                .contains("Hero attacks for 40 damage");

        var expectedRepresentation = "Hero (HP: 90/100, AP: 40, State: Berserk)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation);
    }

    @Test
    void testTakeDamage() {
        var damage = 30;
        character.takeDamage(damage);
        assertThat(character.getCurrentHealth())
                .isEqualTo(character.getMaxHealth() - damage);

        var expectedRepresentation1 = "Hero (HP: 70/100, AP: 20, State: Normal)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation1);
        assertThatThrownBy(() -> character.takeDamage(70))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testTransitionChain() {
        character.poison();
        character.update();
        assertThat(character.getCurrentHealth()).isEqualTo(95);
        assertThat(character.getCurrentAttackPower()).isEqualTo(character.getBaseAttackPower() / 2);
        var expectedRepresentation1 = "Hero (HP: 95/100, AP: 10, State: Poisoned)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation1);

        character.undoSpells();
        character.update();
        assertThat(character.getCurrentHealth()).isEqualTo(95);
        assertThat(character.getCurrentAttackPower())
                .isEqualTo(character.getBaseAttackPower());
        var expectedRepresentation2 = "Hero (HP: 95/100, AP: 20, State: Normal)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation2);

        character.goBerserk();
        character.update();
        assertThat(character.getCurrentHealth()).isEqualTo(95);
        assertThat(character.getCurrentAttackPower())
                .isEqualTo(character.getBaseAttackPower() * 2);
        character.attack();
        assertThat(character.getCurrentHealth()).isEqualTo(85);
        var expectedRepresentation3 = "Hero (HP: 85/100, AP: 40, State: Berserk)";
        assertThat(character.toString()).isEqualTo(expectedRepresentation3);
    }

    @Test
    void testInvariants1() {
        character.takeDamage(95);
        assertThatThrownBy(() -> {
            character.poison();
            character.update();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testInvariants2() {
        character.takeDamage(95);
        character.goBerserk();
        character.update();
        assertThatThrownBy(() -> character.attack()).isInstanceOf(IllegalStateException.class);
    }
}
