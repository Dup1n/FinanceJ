package core_math;

import org.dupin.core_math.NewtonRaphson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewtonRaphsonTest {

    private NewtonRaphson raphson;

    @DisplayName("xLower ~ 1; 2 - e^x = 0; x ~ 0.693...")
    @Test
    void testEquationWithExponential() {
        raphson = new NewtonRaphson() {
            @Override
            public double newtonRoot(double rootValue) {
                return 2 - Math.exp(rootValue);
            }
        };

        double result = raphson.newtRaph(1);

        assertEquals(0.693, result, 1e-3);
        assertEquals(0.0, 2 - Math.exp(result), 1e-3);
    }

    @DisplayName("xLower ~ 2.5; x^3 - 6x^2 + 11x - 6 = 0; x ~ 1")
    @Test
    void testLinearEquation() {
        raphson = new NewtonRaphson(1e-5) {
            @Override
            public double newtonRoot(double rootValue) {
                return Math.pow(rootValue, 3) - 6 * Math.pow(rootValue, 2) + 11 * rootValue - 6;
            }
        };

        double res = raphson.newtRaph(2.5);

        assertEquals(1, res, 1e-3);
        assertEquals(0.0, Math.pow(res, 3) - 6 * Math.pow(res, 2) + 11 * res - 6, 1e-3);
    }

    @DisplayName("xLower ~ 1.5; x - x^2 = 0; x ~ 1")
    @Test
    void testParabolaEquation() {
        raphson = new NewtonRaphson() {
            @Override
            public double newtonRoot(double rootValue) {
                return rootValue - Math.pow(rootValue, 2);
            }
        };

        double res = raphson.newtRaph(1.5);

        assertEquals(1, res, 1e-3);
        assertEquals(0.0, res - Math.pow(res, 2), 1e-3);

        res = raphson.newtRaph(-0.5);

        assertEquals(0, res, 1e-3);
        assertEquals(0.0, res - Math.pow(res, 2), 1e-3);
    }
}
