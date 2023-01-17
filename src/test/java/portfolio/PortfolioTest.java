package portfolio;

import org.dupin.find_apps.Portfolio;
import org.dupin.find_apps.Security;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PortfolioTest {
    private Portfolio portfolio;

    @DisplayName("Expected Portfolio return and starting evaluation")
    @Test
    void testExpectedPortfolioReturn() {
        List<Security> securities = Arrays.asList(
                new Security("January", 100, 40, 0.162),
                new Security("February", 200, 35, 0.246),
                new Security("March", 100, 62, 0.228),
                new Security("April", 150, 30, 0.213),
                new Security("May", 100, 31, 0.221),
                new Security("June", 300, 17, 0.166),
                new Security("July", 180, 22, 0.15),
                new Security("August", 200, 10, 0.137),
                new Security("September", 120, 40, 0.125),
                new Security("Octuber", 100, 54, 0.113)
        );
        portfolio = new Portfolio("Dinamo", securities);

        portfolio.generateProportion();
        portfolio.generateExpPortfolioReturn();

        assertEquals(46060.0, portfolio.getStartingValuation(), 1e-1);
        assertEquals(0.1813, portfolio.getExpectedPortfolioReturn(), 1e-4);
    }

    @DisplayName("Expected Portfolio return and starting evaluation - 2th method")
    @Test
    void testExpectedPortfolioReturnSecondMethod() {
        portfolio = new Portfolio();

        List<Security> securities = Arrays.asList(
                new Security("January", 100, 40, 0.162),
                new Security("February", 200, 35, 0.246),
                new Security("March", 100, 62, 0.228),
                new Security("April", 150, 30, 0.213),
                new Security("May", 100, 31, 0.221),
                new Security("June", 300, 17, 0.166),
                new Security("July", 180, 22, 0.15),
                new Security("August", 200, 10, 0.137),
                new Security("September", 120, 40, 0.125),
                new Security("Octuber", 100, 54, 0.113)
        );

        portfolio.setPortfolioName("Dinamo");
        portfolio.setSecurities(securities);

        portfolio.generateStartValuation();
        portfolio.generateProportion();
        portfolio.generateExpPortfolioReturn();

        assertEquals(46060.0, portfolio.getStartingValuation(), 1e-1);
        assertEquals(0.1813, portfolio.getExpectedPortfolioReturn(), 1e-4);
    }
}
