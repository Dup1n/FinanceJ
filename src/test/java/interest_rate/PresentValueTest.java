package interest_rate;

import org.dupin.interest_rate.PresentValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PresentValueTest {
    PresentValue presentValue;

    @DisplayName("Present Value with cash constant")
    @Test
    void testPresentValueCashConstant() {
        presentValue = new PresentValue();

        assertEquals(27.23, presentValue.presentValue(10, 0.05, 3), 1e-2);
    }

    @DisplayName("Present Value with cashflows")
    @Test
    void testPresentValueCashflows() {
        presentValue = new PresentValue();

        assertEquals(457862.11, presentValue.presentValue(Arrays.asList(35000.0, 37000.0, 38000.0, 40000.0, 491000.0), 0.08), 1e-2);
    }

    @DisplayName("Generate Present Value with payments equals or advance")
    @Test
    void testPresentValueWithPayments() {
        presentValue = new PresentValue();

        assertEquals(27.23, 10 * presentValue.paymentsEqualAmount(0.05, 3), 1e-2);
        assertEquals(28.59, 10 * presentValue.paymentsEqualAdvance(0.05, 3), 1e-2);
        assertEquals(16.3766, presentValue.increasingAnnuality(0.06, 6), 1e-3);
    }
}
