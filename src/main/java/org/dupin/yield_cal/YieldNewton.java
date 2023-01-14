package org.dupin.yield_cal;

import org.dupin.core_math.NewtonRaphson;

import static java.lang.Math.pow;

public class YieldNewton extends NewtonRaphson {

    protected double nominalStockPrice;
    protected double termPeriod;
    protected double couponRate;
    protected double marketPriceValue;
    protected double inputValueLow;
    protected double ratePerTerm;
    protected double maturityPeriod;
    protected double rateIndex;

    public YieldNewton(double low) {
        inputValueLow = low;
    }

    public YieldNewton(double precision, double low) {
        super(precision);
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

        return newtRaph(inputValueLow);
    }

    @Override
    public double newtonRoot(double value) {
        double cashFlow = ratePerTerm;

        return (cashFlow / value) * (1 - (1 / pow(1 + value, rateIndex))) +
                (nominalStockPrice / pow(1 + value, rateIndex)) - marketPriceValue;
    }
}
