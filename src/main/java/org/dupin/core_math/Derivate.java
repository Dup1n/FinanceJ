package org.dupin.core_math;

public abstract class Derivate {
    protected double h;

    protected Derivate(double h){
        this.h = h;
    }

    public double derivation(double inputFunc) {
        double value;

        double x2 = deriveFunction(inputFunc - h);
        double x1 = deriveFunction(inputFunc + h);

        value = ((x1 - x2)/(2*h));
        return value;
    }

    public abstract double deriveFunction(double fx);
}
