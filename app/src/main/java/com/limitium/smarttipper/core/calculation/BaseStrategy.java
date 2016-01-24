package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.InflaterProvider;

import java.util.Observable;

public abstract class BaseStrategy extends Observable implements InflaterProvider {
    private int persons = 1;
    private GreedMode greed = GreedMode.AVERAGE;

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
        recalculate();
    }

    public GreedMode getGreed() {
        return greed;
    }

    public void setGreed(GreedMode greed) {
        this.greed = greed;
        recalculate();
    }

    public void recalculate() {
        setChanged();
        notifyObservers(getTips());
    }

    public float getTips() {
        return calculate(greed) / persons;
    }

    protected abstract float calculate(GreedMode greed);

}
