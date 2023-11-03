package io.jm;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("When running MathUtils")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll - Cleaning up...");
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach - Cleaning up...");
    }

    @Nested
    @DisplayName("add method")
    @Tag("Math")
    class AddTest {
        @Test
        @DisplayName("When adding two positive numbers")
        void testAddPositive() {
            int expected = 4;
            int actual = mathUtils.add(2, 2);
            assertEquals(expected, actual, "should return the right sum");
        }

        @Test
        @DisplayName("When adding two negative numbers")
        void testAddNrgative() {
            int expected = -4;
            int actual = mathUtils.add(-2, -2);
            assertEquals(expected, actual, () -> "should return the right sum '" + expected + "' but return '" + actual + "'");
        }
    }

    @Test
    @Tag("Math")
    @DisplayName("multiply method")
    void testMultiply() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2)),
                () -> assertEquals(0, mathUtils.multiply(2, 0)),
                () -> assertEquals(-2, mathUtils.multiply(2, -1))
        );
    }

    @Test
    @Tag("Math")
    @DisplayName("divide method")
    void testDivide() {
        boolean isServerUp = true;
        assumeTrue(isServerUp);
        Executable executable = () -> mathUtils.divide(1, 0);
        assertThrows(ArithmeticException.class, executable, "Divide by zero should throw");
    }

    @RepeatedTest(5)
    @Tag("Circle")
    void testComputeCircleArea(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() == 1) {
            System.out.println("first repetition");
        }
        var expected = 314.1592653589793;
        var actual = mathUtils.computeCircleArea(10);
        assertEquals(expected, actual, "Should return right circle area");
    }

    @Test
    @Disabled
    @DisplayName("TDD method. Should not run.")
    void testDisabled() {
        fail("This test should be disabled");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindows() {
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinux() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    void testForJre21() {
    }
}
