package org.dupin.find_apps;

import org.dupin.base_stats.DataDispersion;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    private String portfolioName;
    private List<Security> securities;
    private double startingValuation;
    private double expectedPortfolioReturn;

    public Portfolio() {
        this.startingValuation = 0.0;
        this.expectedPortfolioReturn = 0.0;
    }

    public Portfolio(String portfolioName, List<Security> data) {
        this.portfolioName = portfolioName;
        this.securities = data;

        this.startingValuation = data.stream().reduce(0.0,
                (acc, value) -> acc + value.getInvestmentPerShare(),
                Double::sum);

        this.expectedPortfolioReturn = 0.0;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    public double getStartingValuation() {
        return startingValuation;
    }

    public double getExpectedPortfolioReturn() {
        return expectedPortfolioReturn;
    }

    public void generateStartValuation() {
        this.startingValuation = securities.stream().reduce(0.0,
                (acc, value) -> acc + value.getInvestmentPerShare(),
                Double::sum);
    }

    public void generateProportion() {
        securities.forEach((security) -> {
            security.setProportion(security.getInvestmentPerShare() / startingValuation);
        });
    }

    public void generateExpPortfolioReturn() {
        expectedPortfolioReturn = securities.stream().reduce(0.0,
                (acc, security) -> acc + (security.getExpectedReturn() * security.getProportion()), Double::sum);
    }

    @Override
    public String toString() {
        return "SelectPortfolio{" +
                "name='" + portfolioName + '\'' +
                ", Securities=" + securities +
                ", startingValuation=" + startingValuation +
                ", expectedPortfolioReturn=" + expectedPortfolioReturn +
                '}';
    }
}
