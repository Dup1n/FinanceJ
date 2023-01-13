package org.dupin.base_stats;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DataDispersion {

    public static double mean(List<Double> data) {
        int total = data.size();
        return data.stream().reduce(0.0, Double::sum) / total;
    }

    public static double mean(List<Double> data, List<Double> probabilities) {
        if (data.size() == probabilities.size()) {
            AtomicInteger index = new AtomicInteger();
            List<Double> total = data.stream()
                                     .map((value) -> value * probabilities.get(index.getAndIncrement()))
                                     .collect(Collectors.toList());

            double probability = probabilities.stream().reduce(0.0, Double::sum);

            if (probability == 1.0) {
                return total.stream().reduce(0.0, Double::sum);
            }
        }

        return 0.0;
    }

    public static double variance(List<Double> data) {
        int divide = data.size();
        double mean = mean(data);

        double result = data.stream()
                .reduce(0.0, (accumulator, value) ->
                        accumulator + Math.pow(value - mean, 2)
                );

        return result / divide;
    }

    public static double variance2(List<Double> data) {
        int divide = data.size() - 1;
        double mean = mean(data);

        double result = data.stream()
                .reduce(0.0, (accumulator, value) ->
                        accumulator + Math.pow(value - mean, 2)
                );

        return result / divide;
    }

    public static double variance(List<Double> data, List<Double> probabilities) {
        if (data.size() == probabilities.size()) {
            AtomicInteger index = new AtomicInteger();

            double rightValues = Math.pow(mean(data, probabilities), 2);

            double leftValues = data.stream()
                    .map((value) -> Math.pow(value, 2) * probabilities.get(index.getAndIncrement()))
                    .reduce(0.0, Double::sum);

            double probability = probabilities.stream().reduce(0.0, Double::sum);

            if (probability == 1.0) {
                return leftValues - rightValues;
            }
        }

        return 0.0;
    }

    public static double standardDeviation(List<Double> data) {
        return Math.sqrt(variance(data));
    }

    public static double standardDeviation(List<Double> data, List<Double> probabilities) {
        return Math.sqrt(variance(data, probabilities));
    }

    public static double standardDeviation(double variance) {
        return Math.sqrt(variance);
    }

    public static double standardDeviation2(List<Double> data) {
        return Math.sqrt(variance2(data));
    }

    public static List<Double> dumean(List<Double> list1, List<Double> list2) {
        if (list1.size() == list2.size()) {
            return Arrays.asList(mean(list1), mean(list2));
        }
        return Arrays.asList(0.0, 0.0);
    }

    public static List<Double> variances(List<Double> list1, List<Double> list2) {
        if (list1.size() == list2.size()) {
            return Arrays.asList(variance2(list1), variance2(list2));
        }
        return Arrays.asList(0.0, 0.0);
    }

    public static double covar(List<Double> listX, List<Double> listY) {
        if (listX.size() == listY.size()) {
            double meanX = mean(listX);
            double meanY = mean(listY);
            double n = listX.size();
            AtomicInteger index = new AtomicInteger();

            double sum = listX.stream()
                              .map((x_i) -> (x_i - meanX) * (listY.get(index.getAndIncrement()) - meanY))
                              .reduce(0.0, Double::sum);

            return sum / n;
        }
        return 0.0;
    }

    public static double covar2(List<Double> listX, List<Double> listY) {
        if (listX.size() == listY.size()) {
            double meanX = mean(listX);
            double meanY = mean(listY);
            double n = listX.size() - 1;
            AtomicInteger index = new AtomicInteger();

            double sum = listX.stream()
                    .map((x_i) -> (x_i - meanX) * (listY.get(index.getAndIncrement()) - meanY))
                    .reduce(0.0, Double::sum);

            return sum / n;
        }
        return 0.0;
    }

    public static double covar(List<Double> listX, List<Double> listY, List<Double> prob) {
        if (listX.size() == listY.size() && listY.size() == prob.size()) {
            double meanX = mean(listX);
            double meanY = mean(listY);
            AtomicInteger index = new AtomicInteger();

            Double result = listX.stream()
                    .map((x_i) -> (x_i - meanX) * (listY.get(index.get()) - meanY) * prob.get(index.getAndIncrement()))
                    .reduce(0.0, Double::sum);

            return result;
        }
        return 0.0;
    }

    public static double correlation(double cov, double sdX, double sdY) {
        return cov / (sdX * sdY);
    }

    public static double correlation(List<Double> listX, List<Double> listY) {
        return covar(listX, listY) / (standardDeviation(listX) * standardDeviation(listY));
    }

    public static double correlation(List<Double> listX, List<Double> listY, List<Double> prob) {
        return covar(listX, listY, prob) / (standardDeviation(listX, prob) * standardDeviation(listY, prob));
    }
}
