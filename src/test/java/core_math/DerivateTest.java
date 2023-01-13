package core_math;

import org.dupin.core_math.Derivate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DerivateTest {

    private Derivate derivate;

    @DisplayName("x ~ 1; f'(2 - e^x) -> - e^x = - 2.1782...")
    @Test
    void testDerivationExponential() {
        derivate = new Derivate(0.05) {
            @Override
            public double deriveFunction(double fx) {
                return 2 - Math.exp(fx);
            }
        };

        assertEquals(- Math.exp(1), derivate.derivation(1), 1e-2);
    }

    @DisplayName("x ~= 5; f'(x - x^2) -> 1 - 2x ~= -9")
    @Test
    void testDerivationParabola() {
        derivate = new Derivate(0.05) {
            @Override
            public double deriveFunction(double fx) {
                return fx - Math.pow(fx, 2);
            }
        };

        assertEquals(-9, derivate.derivation(5), 1e-5);
    }

    @DisplayName("x ~= 0.5; f'(x^3 - 6x^2 + 11x - 6) -> 3x^2 - 12x + 11 ~= 5.75")
    @Test
    void testDerivationLinealEquation() {
        derivate = new Derivate(0.05) {
            @Override
            public double deriveFunction(double fx) {
                return Math.pow(fx, 3) - 6 * Math.pow(fx, 2) + 11 * fx - 6;
            }
        };

        assertEquals(5.75, derivate.derivation(0.5), 1e-2);
    }

    @DisplayName("x ~= 2; f'(x^4 * sen(x)) -> x^3 (4sen(x) + xcos(x)) ~= 22.43")
    @Test
    void testDerivationTrigonometryEquation() {
        derivate = new Derivate(0.005) {
            @Override
            public double deriveFunction(double fx) {
                return Math.pow(fx, 4) * Math.sin(fx);
            }
        };

        assertEquals(22.43, derivate.derivation(2), 1e-2);
    }

}
