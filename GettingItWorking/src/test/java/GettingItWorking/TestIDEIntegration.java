package GettingItWorking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestIDEIntegration {
    @Test
    void firstExample() {
        Assertions.assertEquals("happy?", "HAPPY?".toLowerCase());
    }
}
