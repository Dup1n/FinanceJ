package interest_rate;

import org.dupin.interest_rate.PresentValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PresentValueTest {
    @DisplayName("Present Value with cash constant")
    @Test
    void testPresentValueCashConstant() {
        assertEquals(27.23, PresentValue.presentValue(10, 0.05, 3), 1e-2);
    }

    @DisplayName("Present Value with cashflows")
    @Test
    void testPresentValueCashflows() {
        assertEquals(457862.11, PresentValue.presentValue(Arrays.asList(35000.0, 37000.0, 38000.0, 40000.0, 491000.0), 0.08), 1e-2);
    }

    @DisplayName("Generate Present Value with payments equals or advance")
    @Test
    void testPresentValueWithPayments() {
        assertEquals(27.23, 10 * PresentValue.paymentsEqualAmount(0.05, 3), 1e-2);
        assertEquals(28.59, 10 * PresentValue.paymentsEqualAdvance(0.05, 3), 1e-2);
        assertEquals(16.3766, PresentValue.increasingAnnuality(0.06, 6), 1e-3);
    }
}
