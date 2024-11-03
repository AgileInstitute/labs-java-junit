package StarTrek;

import java.lang.reflect.Method;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhaserCharacterizationTests {
    private Game game;
    private MockGalaxy context;

    private static final int EnergyInNewGame = 10000;

    @AfterEach
    public void removeTheMockRandomGeneratorForOtherTests_IReallyWantToRefactorThatStaticVariableSoon() {
        Game.generator = new Random();
    }

    @BeforeEach
    public void setUp() {
        game = new Game();
        context = new MockGalaxy();
        context.setValueForTesting("command", "phaser");
    }

    @Test
    public void gameHasFireWeaponSignatureExpectedBySampleClient() {
    	String expectedSignature = "public void StarTrek.Game.fireWeapon(Untouchables.WebGadget)";
    	boolean found = false;
    	for (Method next : Game.class.getDeclaredMethods()) {
    		if (expectedSignature.equals(next.toString()))
    			found = true;
    	}
		  Assertions.assertTrue(
                  found,
                  "The following signature must exist else clients will not compile: " + expectedSignature);
    }

    @Test
    public void PhasersFiredWithInsufficientEnergy() {
        context.setValueForTesting("amount", (EnergyInNewGame + 1) + "");
        game.fireWeapon(context);
        Assertions.assertEquals("Insufficient energy to fire phasers! || ",
            context.getAllOutput());
    }

    @Test
    public void PhasersFiredWhenKlingonOutOfRange_AndEnergyExpendedAnyway() {
        int maxPhaserRange = 4000;
        int outOfRange = maxPhaserRange + 1;
        context.setValueForTesting("amount", "1000");
        context.setValueForTesting("target", new MockKlingon(outOfRange));
        game.fireWeapon(context);
        Assertions.assertEquals("Klingon out of range of phasers at " + outOfRange + " sectors... || ",
            context.getAllOutput());
        Assertions.assertEquals(EnergyInNewGame - 1000, game.EnergyRemaining());
    }

    @Test
    public void PhasersFiredKlingonDestroyed() {
        MockKlingon klingon = new MockKlingon(2000, 200);
        context.setValueForTesting("amount", "1000");
        context.setValueForTesting("target", klingon);
        Game.generator = new MockRandom();
        game.fireWeapon(context);
        Assertions.assertEquals("Phasers hit Klingon at 2000 sectors with 400 units || Klingon destroyed! || ",
            context.getAllOutput());
        Assertions.assertEquals(EnergyInNewGame - 1000, game.EnergyRemaining());
        Assertions.assertTrue(klingon.deleteWasCalled());
    }

    @Test
    public void PhasersDamageOfZeroStillHits_AndNondestructivePhaserDamageDisplaysRemaining() {
        String minimalFired = "0";
        String minimalHit = "1";
        context.setValueForTesting("amount", minimalFired);
        context.setValueForTesting("target", new MockKlingon(2000, 200));
        Game.generator = new MockRandom();
        game.fireWeapon(context);
        Assertions.assertEquals("Phasers hit Klingon at 2000 sectors with " +
            minimalHit + " units || Klingon has 199 remaining || ",
            context.getAllOutput());
        // Isn't this also a bug?  I *ask* to fire zero, and I still hit?
        // Acknowledge it, log it, but don't fix it yet!
    }

}
