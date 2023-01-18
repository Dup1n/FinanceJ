package interest_rate;

import org.dupin.interest_rate.CompoundInterest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompoundInterestTest {
    CompoundInterest compoundInterest;

    @DisplayName("Analyze real interest rate")
    @Test
    void realInterestRateTest() {
        compoundInterest = new CompoundInterest(155, 159);

        assertEquals(0.0528, compoundInterest.realInterestRate(0.08), 1e-4);
    }

    @DisplayName("Analyze effective Annual Interest Test")
    @Test
    void methodsForInterestTest() {
        assertEquals(0.0920, CompoundInterest.effectiveRateInterest(0.089, 4), 1e-4);
        assertEquals(0.088, CompoundInterest.forceInterest(0.092), 1e-4);
        assertEquals(6.975, CompoundInterest.paymentsInArrears(0.06, 6), 1e-3);
        assertEquals(7.393, CompoundInterest.paymentsInAdvance(0.06, 6), 1e-3);
    }
}
