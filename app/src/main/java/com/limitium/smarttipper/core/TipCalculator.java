package com.limitium.smarttipper.core;

public class TipCalculator {
    private int persons = 1;
    private GreedMode greed = GreedMode.AVERAGE;
    private TipProvider tipProvider;

    public float getTip() {
        return tipProvider.getTipStrategy().getTips(greed) / persons;
    }

    public int getPersons() {
        return persons;
    }


    public void setPersons(int persons) {
        this.persons = persons;
    }

    public GreedMode getGreed() {
        return greed;
    }

    public void setGreed(GreedMode greed) {
        this.greed = greed;
    }

    public TipProvider getTipProvider() {
        return tipProvider;
    }

    public void setTipProvider(TipProvider tipProvider) {
        this.tipProvider = tipProvider;
    }
}
