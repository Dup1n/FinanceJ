package org.dupin.yield_cal;

import static java.lang.Math.pow;
import org.dupin.core_math.IntervalBisection;

public class YieldBisect extends IntervalBisection {

    protected double nominalStockPrice;
    protected double termPeriod;
    protected double couponRate;
    protected double marketPriceValue;
    protected double inputValueLow;
    protected double inputValueHigh;
    protected double ratePerTerm;
    protected double maturityPeriod;
    protected double rateIndex;

    public YieldBisect(double high, double low) {
        inputValueHigh = high;
        inputValueLow = low;
    }

    public YieldBisect(int iterations, double precision, double high, double low) {
        super(iterations, precision);
        inputValueHigh = high;
        inputValueLow = low;
    }

    public double yieLd(double noms, double term, double coupon,
                        double mktp, double period) {
        nominalStockPrice = noms;
        termPeriod = term;
        couponRate = coupon;
        marketPriceValue = mktp;
        ratePerTerm = ((coupon/term));
        maturityPeriod = period;
        rateIndex = (maturityPeriod * term);

        return evaluateRoot(inputValueLow,inputValueHigh);
    }

    @Override
    public double computeFunction(double value) {
        double cashFlow = ratePerTerm;

        return (cashFlow / value) * (1 - (1 / pow(1 + value, rateIndex))) +
                    (nominalStockPrice / pow(1 + value, rateIndex)) - marketPriceValue;
    }
}
