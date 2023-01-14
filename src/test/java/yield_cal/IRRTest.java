package yield_cal;

import org.dupin.yield_cal.IRRBisect;
import org.dupin.yield_cal.IRRNewton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IRRTest {
    IRRBisect calcIRRBisect;
    IRRNewton calcIRRNewton;
    double high = 1.0;
    double low = 0.0;

    @BeforeEach
    void setUp() {
        calcIRRBisect = new IRRBisect(high, low);
        calcIRRNewton = new IRRNewton(low);
    }

    @DisplayName("Calculate Internal Rate Return")
    @ParameterizedTest
    @MethodSource("valuesToIRR")
    void testCalcIRR(double investedAmount, List<Double> cashes, double expected) {
        double resultBisect = calcIRRBisect.iRR(investedAmount, cashes);
        double resultNewton = calcIRRNewton.iRR(investedAmount, cashes);

        assertAll("IRR calculate with Bisection and NewtonRapshon formula",
                () -> assertEquals(expected, resultBisect, 1e-4),
                () -> assertEquals(expected, resultNewton, 1e-4)
        );
    }

    private static Stream<Arguments> valuesToIRR() {
        return Stream.of(
                Arguments.arguments(500, Arrays.asList(570.0), 0.14),
                Arguments.arguments(2000, Arrays.asList(100.0, 100.0, 2600.0), 0.1238),
                Arguments.arguments(500, Arrays.asList(160.0, 160.0, 160.0, 160.0, 50.0), 0.1327),
                Arguments.arguments(5000, Arrays.asList(1700.0, 1900.0, 1600.0, 1500.0, 700.0), 0.1661),
                Arguments.arguments(2000, Arrays.asList(400.0, 700.0, 500.0, 400.0, 300.0), 0.0523)
        );
    }
}
