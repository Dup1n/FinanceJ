package org.dupin.interest_rate;

public final class CompoundInterest {
    private double costLivingStart;
    private double costLivingEnd;

    public CompoundInterest(double costLivingStart, double costLivingEnd) {
        this.costLivingStart = costLivingStart;
        this.costLivingEnd = costLivingEnd;
    }

    public double realInterestRate(double nominalInterestRate) {
        return (costLivingStart * (1 + nominalInterestRate)) / costLivingEnd - 1;
    }

    public static double effectiveRateInterest(double nominalInterest, double conversionPerAnn) {
        return Math.pow(1 + nominalInterest/conversionPerAnn, conversionPerAnn) - 1;
    }

    public static double forceInterest(double effectiveAnnualRete) {
        return Math.log(effectiveAnnualRete + 1);
    }

    public static double paymentsInArrears(double interestRate, int periods) {
        return (Math.pow(1 + interestRate, periods) - 1) / interestRate;
    }

    public static double paymentsInAdvance(double interestRate, int periods) {
        return paymentsInArrears(interestRate, periods) * (1 + interestRate);
    }

}
