package test.java;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.platform.commons.annotation.Testable;

import UI.Engine;

@Testable
public class TestingEnvironment {
	Engine eng;

    @BeforeEach                                       
    void setUp() {
        eng = new Engine();
    }

    @Test
    @DisplayName("Testing generation of new Engine")
    void testEng() {
    	eng.run();
    }
}
