package org.dupin.interest_rate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TermStructures {

    public double disFromyld(double spotrate, double time) {
        return Math.exp(-spotrate * time);
    }

    public double yldFromdisc(double discount, double time) {
        return - Math.log(discount) / time;
    }

    public double forateFromspts(double spot1, double spot2) {
        return Math.pow(1 + spot2, 2) / (1 + spot1) - 1;
    }

    public double forateFromdisc(double discount1, double discount2, double time1, double time2) {
        return Math.log(discount1 / discount2) / (time2 - time1);
    }

    public double forateFromyld(double r1, double r2, double t1, double t2) {
        return (r2 * (t2 / (t2 - t1))) - (r1 * (t1 /(t2 - t1)));
    }

    public double spotRate(double price, double coupon, List<Double> yields) {
        int period = 1;
        double sumFactor = 0.0;

        for (double yield: yields) {
            sumFactor += 1 / Math.pow(1 + yield, period);
            period++;
        }

        return Math.pow(facto(price, coupon, sumFactor, 1), (double) 1 / (period)) - 1 ;
    }

    public double facto(double price, double coupon, double spots, int periods) {
        return ((coupon / periods) + 100) / (price - ((coupon / periods) * spots));
    }

    public List<Double> spotFcoupon(List<List<Double>> pcData) {
        return spotFcoupon(pcData, 1);
    }

    public List<Double> spotFcoupon(List<List<Double>> pcData, int periods) {
        int n = pcData.size();
        double index = 1.0;
        List<Double> spots = new ArrayList<>();

        double price = pcData.get(0).get(0) / 100.0;
        spots.add((100 / pcData.get(0).get(0)) - 1);

        for (int s = 1; s < n; s++) {
            index++;
            spots.add(Math.exp(1 / index * Math.log(facto(pcData.get(s).get(0), pcData.get(s).get(1), price, periods))) - 1);
            price += (Math.exp(-index * Math.log(1 + spots.get(s))));
        }
        return spots;
    }

    public double parCoupon(List<Double> spots,int nperiod)
    {
        if(spots.size() == nperiod) {
            AtomicInteger i = new AtomicInteger(1);

            double finaldisc = (1.0 - (Math.exp(-nperiod * Math.log(1.0 + spots.get(nperiod - 1)))));
            double flowdisc = spots.stream().reduce(0.0, (acc, spot) -> acc + Math.exp(-i.getAndIncrement() * Math.log(1.0 + spot)));

            return  finaldisc / flowdisc;
        }

        return -1.0;
    }
}
