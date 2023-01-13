package base_stats;

import org.dupin.base_stats.DataDispersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDispersionTest {

    List<Double> dataX;
    List<Double> dataY;
    List<Double> prob;

    @BeforeEach()
    void setUp() {
        dataX = new ArrayList<>(Arrays.asList(12.0, 7.0, 11.0, 23.0, 44.0, 58.0, 22.0, 33.0, 56.0, 76.0));
        dataY = new ArrayList<>(Arrays.asList(12.0, 6.0, 11.0, 21.0, 44.0, 48.0, 22.0, 33.0, 56.0, 76.0));
        prob = new ArrayList<>(Arrays.asList(0.10, 0.2, 0.10, 0.10, .075, .025, 0.20, 0.10, 0.05, 0.05));
    }

    @DisplayName("Mean Sample")
    @Test
    void testMean() {
        assertEquals(34.2, DataDispersion.mean(dataX), 1e-3);
        assertEquals(32.9, DataDispersion.mean(dataY), 1e-3);
    }

    @DisplayName("Mean Sample with Probability")
    @Test
    void testMeanWithProbability() {
        assertEquals(25.05, DataDispersion.mean(dataX, prob), 1e-3);
        assertEquals(24.4, DataDispersion.mean(dataY, prob), 1e-3);
    }

    @DisplayName("Variance Sample")
    @Test
    void testVariance() {
        assertEquals(547.955, DataDispersion.variance2(dataX), 1e-3);
        assertEquals(515.877, DataDispersion.variance2(dataY), 1e-3);
    }

    @DisplayName("Variance Sample with Probability")
    @Test
    void testVarianceWithProbability() {
        assertEquals(342.2975, DataDispersion.variance(dataX, prob), 1e-3);
        assertEquals(336.5399, DataDispersion.variance(dataY, prob), 1e-3);
    }

    @DisplayName("Standard Deviation")
    @Test
    void testStandardDeviation() {
        double variance = DataDispersion.variance2(dataX);
        double varianceP = DataDispersion.variance(dataX, prob);

        assertEquals(23.408, DataDispersion.standardDeviation(variance), 1e-3);
        assertEquals(18.501, DataDispersion.standardDeviation(varianceP), 1e-3);

        assertEquals(DataDispersion.standardDeviation(variance),
                        DataDispersion.standardDeviation2(dataX), 1e-3);

        assertEquals(DataDispersion.standardDeviation(varianceP),
                        DataDispersion.standardDeviation(dataX, prob), 1e-3);
    }

    @DisplayName("Mean with two Sample")
    @Test
    void testMeanTwoSamples() {
        List<Double> results = DataDispersion.dumean(dataX, dataY);

        assertEquals(34.2, results.get(0), 1e-3);
        assertEquals(32.9, results.get(1), 1e-3);
    }

    @DisplayName("Variance with two Sample")
    @Test
    void testVarianceTwoSamples() {
        List<Double> results = DataDispersion.variances(dataX, dataY);

        assertEquals(547.955, results.get(0), 1e-3);
        assertEquals(515.877, results.get(1), 1e-3);
    }

    @DisplayName("Covariance")
    @Test
    void testCovariance() {
        assertEquals(474.32, DataDispersion.covar(dataX, dataY), 1e-2);
        assertEquals(415.85, DataDispersion.covar(dataX, dataY, prob), 1e-2);
    }

    @DisplayName("Correlation")
    @Test
    void testCorrelation() {
        double cov = DataDispersion.covar(dataX, dataY);
        double sdvX = DataDispersion.standardDeviation(dataX);
        double sdvY = DataDispersion.standardDeviation(dataY);

        double result = DataDispersion.correlation(cov, sdvX, sdvY);

        assertEquals(0.991, result, 1e-3);
        assertEquals(result, DataDispersion.correlation(dataX, dataY), 1e-3);
        assertEquals(1.225, DataDispersion.correlation(dataX, dataY, prob), 1e-3);
    }
}
