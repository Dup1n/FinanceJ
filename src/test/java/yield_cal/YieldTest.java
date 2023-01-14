package yield_cal;

import org.dupin.yield_cal.IRRBisect;
import org.dupin.yield_cal.YieldBisect;
import org.dupin.yield_cal.YieldNewton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YieldTest {

    YieldBisect calcYieldBisect;
    YieldNewton calcYieldNewton;

    @BeforeEach
    void setUp() {
        calcYieldBisect = new YieldBisect(20, 1e-6, 0.07, 0.03);
        calcYieldNewton = new YieldNewton(1e-6, 0.03);
    }

    @DisplayName("Calculate yield bound")
    @Test
    void testCalcBond() {
        double resultBisect = calcYieldBisect.yieLd(100.0, 2.0, 10.0, 104.5, 3.0);
        double resultNewton = calcYieldNewton.yieLd(100.0, 2.0, 10.0, 104.5, 3.0);

        assertEquals(0.041377, resultBisect, 1e-5); //4.13%
        assertEquals(0.041377, resultNewton, 1e-5); //4.13%
    }
}
