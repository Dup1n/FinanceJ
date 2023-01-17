package org.dupin.yield_cal;

import org.dupin.core_math.IntervalBisection;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IRRBisect extends IntervalBisection {

    protected List<Double> cashflows;
    protected double initialInvestment;
    protected double highValue;
    protected double lowValue;

    public IRRBisect(double high, double low) {
        highValue = high;
        lowValue = low;
    }

    public double iRR(double investedAmount, List<Double> cashs) {
        initialInvestment = investedAmount;
        cashflows = cashs;
        return evaluateRoot(lowValue, highValue);
    }

    @Override
    public double computeFunction(double rootValue) {
        AtomicInteger timePeriod = new AtomicInteger(1);

        return cashflows.stream()
                .reduce(0.0, (prevValue, cashflow) -> prevValue +
                        cashflow / Math.pow(1 + rootValue, timePeriod.getAndIncrement())
                ) - initialInvestment;
    }
}
