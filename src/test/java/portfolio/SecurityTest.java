package portfolio;

import org.dupin.find_apps.Security;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityTest {
    private Security security;

    String textToTest(String name, double noShares, double initialPrice, double expectedReturn,
                      double investmentPerShare, double proportion) {
        return "{" +
                "name='" + name + '\'' +
                ", noShares=" + noShares +
                ", initialPrice=" + initialPrice +
                ", expectedReturn=" + expectedReturn +
                ", investmentPerShare=" + investmentPerShare +
                ", proportion=" + proportion +
                '}'+'\n';
    }

    @DisplayName("Creating securities")
    @ParameterizedTest
    @MethodSource("securitiesToTest")
    void testSecurityCreated(String name, double noShares, double initialPrice,
                                double expReturn, double invPerShare, double proportion) {
        security = new Security(name, noShares, initialPrice, expReturn);
        assertEquals(security.toString(), textToTest(name, noShares, initialPrice, expReturn, invPerShare, proportion));
    }

    private static Stream<Arguments> securitiesToTest() {
        return Stream.of(
                Arguments.arguments("Asset A", 100.0, 40.0, 0.162, 4000.0, 0.0),
                Arguments.arguments("Asset B", 200.0, 35.0, 0.246, 7000.0, 0.0),
                Arguments.arguments("Asset C", 100.0, 62.0, 0.228, 6200.0, 0.0),
                Arguments.arguments("Asset D", 150.0, 30.0, 0.213, 4500.0, 0.0),
                Arguments.arguments("Asset E", 100.0, 31.0, 0.221, 3100.0, 0.0),
                Arguments.arguments("Asset F", 300.0, 17.0, 0.166, 5100.0, 0.0)
        );
    }
}
