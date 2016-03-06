package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.BartenderInflater;
import com.limitium.smarttipper.core.inflaters.Inflater;
import com.limitium.smarttipper.core.inflaters.TotalInflater;

public class BartenderStrategy extends PercentTipStrategy {
    protected final float low;
    protected final float average;
    protected final float high;
    private final float hiCoc;
    private final float lowBeer;
    private final float aveCoc;
    private final float lowCoc;
    private final float aveBeer;
    private final float hiBeer;
    private boolean isLargeTab;
    private int beers;
    private int cocktails;

    public BartenderStrategy(float lowBeer, float lowCoc, float aveBeer, float aveCoc, float hiBeer, float hiCoc, float low, float average, float high) {
        super(low, average, high);
        this.lowBeer = lowBeer;
        this.lowCoc = lowCoc;
        this.aveBeer = aveBeer;
        this.aveCoc = aveCoc;
        this.hiBeer = hiBeer;
        this.hiCoc = hiCoc;
        this.low = low;
        this.average = average;
        this.high = high;

    }

    public void setIsLargeTab(boolean isLargeTab) {
        this.isLargeTab = isLargeTab;
        recalculate();
    }

    public boolean isLargeTab() {
        return isLargeTab;
    }

    public int getBeers() {
        return beers;
    }

    public void setBeers(int beers) {
        this.beers = beers;
        recalculate();
    }

    public int getCocktails() {
        return cocktails;
    }

    public void setCocktails(int cocktails) {
        this.cocktails = cocktails;
        recalculate();
    }

    @Override
    protected float calculate(GreedMode greed) {
        if (isLargeTab) {
            return super.calculate(greed);
        }
        if (greed == GreedMode.LOW) {
            return beers * lowBeer + cocktails * lowCoc;
        }
        if (greed == GreedMode.AVERAGE) {
            return beers * aveBeer + cocktails * aveCoc;
        }
        return beers * hiBeer + cocktails * hiCoc;
    }

    @Override
    public Inflater getInflater() {
        return new BartenderInflater();
    }

}
