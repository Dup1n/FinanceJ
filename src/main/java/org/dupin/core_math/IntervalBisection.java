package org.dupin.core_math;

public abstract class IntervalBisection {

    protected double precisionValue;
    protected int iterations;

    protected IntervalBisection() {
        iterations = 20;
        precisionValue = 1e-3;
    }

    protected IntervalBisection(int iterations, double precisionValue) {
        this.iterations = iterations;
        this.precisionValue = precisionValue;
    }

    public double evaluateRoot(double lower, double higher) {
        double resultLow;
        double resultHigh;
        double resultMiddle;
        double midValue = 0;
        int iter = 0;

        resultLow = computeFunction(lower);
        resultHigh = computeFunction(higher);

        if (resultHigh * resultLow < 0) {
            do {

                midValue = lower + 0.5 * (higher - lower);
                resultMiddle = computeFunction(midValue);

                if (resultLow * resultMiddle < 0) {
                    higher = midValue;
                } else if (resultLow * resultMiddle > 0) {
                    lower = midValue;
                }

                iter++;

            } while (Math.abs(resultMiddle) > precisionValue && iter < iterations);
        }

        return midValue;
    }

    public abstract double computeFunction(double rootValue);
}
