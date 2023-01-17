package org.dupin.find_apps;

public class Security {

    private String name;
    private double noShares;
    private double initialPrice;
    private double expectedReturn;
    private double investmentPerShare;
    private double proportion;

    public Security() {
        this.initialPrice = 0.0;
        this.expectedReturn = 0.0;
    }

    public Security(String name, double noShares, double initialPrice, double expectedReturn) {
        this.name = name;
        this.noShares = noShares;
        this.initialPrice = initialPrice;
        this.expectedReturn = expectedReturn;
        this.investmentPerShare = initialPrice * noShares;
        this.proportion = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNoShares() {
        return noShares;
    }

    public void setNoShares(double noShares) {
        this.investmentPerShare = this.initialPrice != 0.0 ? this.initialPrice * noShares : 0.0;
        this.noShares = noShares;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.investmentPerShare = this.noShares != 0.0 ? this.noShares * initialPrice : 0.0;
        this.initialPrice = initialPrice;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public double getInvestmentPerShare() {
        return investmentPerShare;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public double getProportion() {
        return proportion;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", noShares=" + noShares +
                ", initialPrice=" + initialPrice +
                ", expectedReturn=" + expectedReturn +
                ", investmentPerShare=" + investmentPerShare +
                ", proportion=" + proportion +
                '}'+'\n';
    }
}
