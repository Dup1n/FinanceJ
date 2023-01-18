package org.dupin.interest_rate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PresentValue {

    private static double divide(double r, double n) {
        return Math.pow(1 + r, n);
    }

    /**
     * The Present Value Factor is the factor that is used to indicate the present value of the cash to be received in
     * the future and is based on the time value of money.
     * @param r = rate of return
     * @param n = number of periods
     * @return = Present Value Factor
     */
    public static double presentValueFactor(double r, double n) {
        return 1 / divide(r, n);
    }

    public static double presentValue(double fV, double r, double n) {
        double pV = 0.0;

        for (int t = 1; t <= n; t++) {
            pV += fV / divide(r, t);
        }

        return pV;
    }

    public static double presentValue(List<Double> fV, double r) {
        double pV = 0.0;

        for (int t = 1; t <= fV.size(); t++) {
            pV += fV.get(t-1) / divide(r, t);
        }

        return pV;
    }

    public static double paymentsEqualAmount(double r, double n) {
        return (1 - (1 / divide(r, n))) / r;
    }

    public static double paymentsEqualAdvance(double r, double n) {
        return paymentsEqualAmount(r, n) * (1 + r);
    }

    public static double perpetuity(double cash, double rate) {
        return cash / rate;
    }

    public static double perpetuity(double cash, double rate, double growth) {
        if (rate <= growth) {
            return perpetuity(cash, rate);
        }
        return cash / (rate - growth);
    }

    public static double increasingAnnuality(double rate, double periods) {
        return (paymentsEqualAdvance(rate, periods) - periods * Math.pow(1 / (1 + rate), periods)) / rate;
    }
}
