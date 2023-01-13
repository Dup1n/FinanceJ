package org.dupin;

import org.dupin.base_stats.DataDispersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> data = new ArrayList<>(Arrays.asList(12.0, 7.0, 11.0, 23.0, 44.0, 58.0, 22.0, 33.0, 56.0, 76.0));
        List<Double> prob = new ArrayList<>(Arrays.asList(0.10, 0.2, 0.10, 0.10, .075, .025, 0.20, 0.10, 0.05, 0.05));

        double varianceData = DataDispersion.variance(data);
        double varianceProb = DataDispersion.variance(data, prob);

        double standevData = DataDispersion.standardDeviation(varianceData);
        double standevProb = DataDispersion.standardDeviation(varianceProb);

        System.out.println(varianceData + "\t" + standevData);
        System.out.println(varianceProb + "\t" + standevProb);

    }
}