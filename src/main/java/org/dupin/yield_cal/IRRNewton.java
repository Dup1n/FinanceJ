package org.dupin.yield_cal;

import org.dupin.core_math.NewtonRaphson;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IRRNewton extends NewtonRaphson {
    protected List<Double> cashflows;
    protected double initialInvestment;
    protected double lowValue;

    public IRRNewton(double low) {
        lowValue = low;
    }

    public double iRR(double investedAmount, List<Double> cashs) {
        initialInvestment = investedAmount;
        cashflows = cashs;
        return newtRaph(lowValue);
    }

    @Override
    public double newtonRoot(double rootValue) {
        AtomicInteger timePeriod = new AtomicInteger(1);

        return cashflows.stream()
                .reduce(0.0, (prevValue, cashflow) -> prevValue +
                        cashflow / Math.pow(1 + rootValue, timePeriod.getAndIncrement())
                ) - initialInvestment;
    }
}
