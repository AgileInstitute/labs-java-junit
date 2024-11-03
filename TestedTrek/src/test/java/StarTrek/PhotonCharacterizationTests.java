package StarTrek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class PhotonCharacterizationTests {
    private Game game;
    private MockGalaxy context;

    @AfterEach
    void RemoveTheMockRandomGeneratorForOtherTests_IReallyWantToRefactorThatStaticVariableSoon() {
        Game.generator = new Random();
    }

    @BeforeEach
    void setUp() {
        game = new Game();
        context = new MockGalaxy();
        context.setValueForTesting("command", "photon");
    }

    @Test
    void NotifiedIfNoTorpedoesRemain() {
        game.setTorpedoes(0);
        context.setValueForTesting("target", new MockKlingon(2000, 200));
        game.fireWeapon(context);
        Assertions.assertEquals("No more photon torpedoes! || ",
                context.getAllOutput());
    }

    @Test
    void TorpedoMissesDueToRandomFactors() {
        int distanceWhereRandomFactorsHoldSway = 2500;
        context.setValueForTesting("target", new MockKlingon(distanceWhereRandomFactorsHoldSway, 200));
        Game.generator = new MockRandom(); // without this the test would often fail
        game.fireWeapon(context);
        Assertions.assertEquals("Torpedo missed Klingon at 2500 sectors... || ",
                context.getAllOutput());
        Assertions.assertEquals(7, game.getTorpedoes());
    }

    @Test
    void TorpedoMissesDueToDistanceAndCleverKlingonEvasiveActions() {
        int distanceWhereTorpedoesAlwaysMiss = 3500;
        context.setValueForTesting("target", new MockKlingon(distanceWhereTorpedoesAlwaysMiss, 200));
        game.fireWeapon(context);
        Assertions.assertEquals("Torpedo missed Klingon at 3500 sectors... || ",
                context.getAllOutput());
        Assertions.assertEquals(7, game.getTorpedoes());
    }

    @Test
    void TorpedoDestroysKlingon() {
        MockKlingon klingon = new MockKlingon(500, 200);
        context.setValueForTesting("target", klingon);
        Game.generator = new MockRandom();
        game.fireWeapon(context);
        Assertions.assertEquals("Photons hit Klingon at 500 sectors with 825 units || Klingon destroyed! || ",
                context.getAllOutput());
        Assertions.assertEquals(7, game.getTorpedoes());
        Assertions.assertTrue(klingon.deleteWasCalled());

    }

    @Test
    void TorpedoDamagesKlingon() {
        context.setValueForTesting("target", new MockKlingon(500, 2000));
        Game.generator = new MockRandom();
        game.fireWeapon(context);
        Assertions.assertEquals("Photons hit Klingon at 500 sectors with 825 units || Klingon has 1175 remaining || ",
                context.getAllOutput());
        Assertions.assertEquals(7, game.getTorpedoes());
    }

}
