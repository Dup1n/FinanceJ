package interest_rate;

import org.dupin.interest_rate.TermStructures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TermStructuresTest {
    TermStructures termStructures;

    @Test
    void testMethodsForTermStructures() {
        termStructures = new TermStructures();

        assertEquals(0.9323, termStructures.disFromyld(0.07, 1), 1e-4);
        assertEquals(0.8650, termStructures.disFromyld(0.0725, 2), 1e-4);
        assertEquals(0.0750, termStructures.forateFromspts(0.07, 0.0725), 1e-4);
        assertEquals(0.0750, termStructures.forateFromyld(0.07, 0.0725, 1, 2), 1e-4);

        assertEquals(0.0512, termStructures.yldFromdisc(0.95, 1), 1e-4);
        assertEquals(0.0961, termStructures.yldFromdisc(0.825, 2), 1e-4);
        assertEquals(0.141, termStructures.forateFromdisc(0.95, 0.825, 1, 2), 1e-4);
    }

    @Test
    @DisplayName("nth-Spot Rate with price, coupon and spots rate")
    void testSpotRate() {
        termStructures = new TermStructures();

        assertEquals(0.1338, termStructures.spotRate(95, 10, Arrays.asList(0.06)), 1e-4);

        assertEquals(0.0236, termStructures.spotRate(99.735, 2.26, Arrays.asList(0.0128, 0.0183)), 1e-4);
        assertEquals(0.0324, termStructures.spotRate(99.908, 3.16, Arrays.asList(0.0128, 0.0183, 0.0236, 0.0277)), 1e-4);
    }

    @Test
    @DisplayName("Test Spot rate with List of prices and Coupon")
    void testListSpotRate() {
        termStructures = new TermStructures();

        List<List<Double>> testData = Arrays.asList(
                Arrays.asList(98.736, 0.0),
                Arrays.asList(99.908, 1.78),
                Arrays.asList(99.735, 2.26),
                Arrays.asList(99.908, 3.16),
                Arrays.asList(99.822, 3.67),
                Arrays.asList(99.675, 4.14),
                Arrays.asList(98.759, 4.92)
        );

        List<Double> ansx = termStructures.spotFcoupon(testData);

        assertEquals(0.0183, ansx.get(1), 1e-4);
        assertEquals(0.0236, ansx.get(2), 1e-4);
        assertEquals(0.0324, ansx.get(3), 1e-4);
    }

    @Test
    @DisplayName("Test Calculate the required Coupon")
    void testCalCoupon() {
        termStructures = new TermStructures();
        assertEquals(0.02343, termStructures.parCoupon(Arrays.asList(0.0128, 0.0183, 0.0236), 3), 1e-4);
    }
}
