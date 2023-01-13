package org.dupin.core_math;

import static java.lang.Math.abs;

public abstract class NewtonRaphson extends Derivate {
    protected double precisionValue;

    public NewtonRaphson() {
        super(1e-5);
        this.precisionValue = h;
    }

    public NewtonRaphson(double precisionValue) {
        super(precisionValue);
        this.precisionValue = precisionValue;
    }

    public double newtRaph(double lowerBound) {
        double fx = newtonRoot(lowerBound);
        double Fx = derivation(lowerBound);
        double x = (lowerBound - (fx/Fx));
        double difference = abs(abs(x) - abs(lowerBound));

        if (difference > precisionValue) {
            lowerBound = x;
            x = newtRaph(lowerBound);
        }

        return x;
    }

    @Override
    public double deriveFunction(double fx) {
        return newtonRoot(fx);
    }

    public abstract double newtonRoot(double rootValue);
}
