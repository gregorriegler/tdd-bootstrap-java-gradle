import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RpgCombatTest {


    @Test
    void healCharacterByAmountIncreasesItsHealth() {
        var character = new RpgCharacter();
        var attacker = new RpgCharacter();
        character.damage(attacker, 100);

        character.heal(10);
        assertIsAlive(character, 910);
    }

    @Test
    void healCharacterByAmountMaxesOutAt1000Health() {
        var character = new RpgCharacter();

        character.heal(10);
        assertIsAlive(character, 1000);
    }

    @Test
    void inflictDamageOnCharacterDecreasesItsHealth() {
        var victim = new RpgCharacter();
        var attacker = new RpgCharacter();

        victim.damage(attacker, 100);

        assertIsAlive(victim, 900);
    }

    @Test
    void inflictedDamageGreaterThanHealthKillsCharacter() {
        RpgCharacter character = new RpgCharacter();
        RpgCharacter attacker = new RpgCharacter();

        character.damage(attacker, 1001);

        assertThat(character.getHealth()).isEqualTo(0);
        assertThat(character.isAlive()).isFalse();
    }

    @Test
    void attackerCannotInflictDamageOnHimself() {
        var attacker = new RpgCharacter();

        attacker.damage(attacker, 100);

        assertIsAlive(attacker, 1000);
    }

    private void assertIsAlive(RpgCharacter character, int expectedHealth) {
        assertThat(character.getHealth()).isEqualTo(expectedHealth);
        assertThat(character.isAlive()).isTrue();
    }

}

class RpgCharacter {
    private int health = 1000;
    private boolean alive = true;

    public void heal(int amount) {
        this.health = Math.min(1000, this.health + amount);
    }

    public int getHealth() {
        return health;
    }

    public void damage(RpgCharacter attacker, int damage) {
        if (this == attacker) {
            return;
        }

        this.health -= damage;
        if(this.health < 0) {
            this.health = 0;
            this.alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }
}

