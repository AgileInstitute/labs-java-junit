package GettingItWorking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestIDEIntegration {
    @Test
    public void firstExample() {
        Assertions.assertEquals("happy?", "HAPPY?".toLowerCase());
    }
}
