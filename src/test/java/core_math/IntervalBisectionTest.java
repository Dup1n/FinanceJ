package core_math;

import org.dupin.core_math.IntervalBisection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntervalBisectionTest {

    private IntervalBisection bisection;

    @DisplayName("xLower ~ 1; xHigher ~ 0.5; 2 - e^x = 0; x ~ 0.693...")
    @Test
    void testEquationWithExponential() {
        bisection = new IntervalBisection() {
            @Override
            public double computeFunction(double rootValue) {
                return 2 - Math.exp(rootValue);
            }
        };

        double result = bisection.evaluateRoot(1, 0.5);

        assertEquals(0.693, result, 1e-3);
        assertEquals(0.0, 2 - Math.exp(result), 1e-3);
    }

    @DisplayName("xLower ~ 2.5; xHigher ~ 1.5; x^3 - 6x^2 + 11x - 6 = 0; x ~ 2")
    @Test
    void testLinearEquation() {
        bisection = new IntervalBisection(10, 1e-5) {
            @Override
            public double computeFunction(double rootValue) {
                return Math.pow(rootValue, 3) - 6 * Math.pow(rootValue, 2) + 11 * rootValue - 6;
            }
        };

        double res = bisection.evaluateRoot(2.5, 1.5);

        assertEquals(2, res, 1e-3);
        assertEquals(0.0, Math.pow(res, 3) - 6 * Math.pow(res, 2) + 11 * res - 6, 1e-3);
    }

    @DisplayName("xLower ~ 1.5; xHigher ~ 0.5; x - x^2 = 0; x ~ 1")
    @Test
    void testParabolaEquation() {
        bisection = new IntervalBisection(10, 1e-5) {
            @Override
            public double computeFunction(double rootValue) {
                return rootValue - Math.pow(rootValue, 2);
            }
        };

        double res = bisection.evaluateRoot(1.5, 0.5);

        assertEquals(1, res, 1e-3);
        assertEquals(0.0, res - Math.pow(res, 2), 1e-3);

        res = bisection.evaluateRoot(-0.5, 0.5);

        assertEquals(0, res, 1e-3);
        assertEquals(0.0, res - Math.pow(res, 2), 1e-3);
    }
}
