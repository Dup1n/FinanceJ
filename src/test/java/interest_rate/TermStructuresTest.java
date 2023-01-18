package interest_rate;

import org.dupin.interest_rate.TermStructures;
import org.junit.jupiter.api.Test;

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
}
