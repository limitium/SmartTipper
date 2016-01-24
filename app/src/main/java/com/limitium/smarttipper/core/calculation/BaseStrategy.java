package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.InflaterProvider;

import java.util.Observable;
import java.util.Observer;

public abstract class BaseStrategy implements InflaterProvider {
    private int persons = 1;
    private GreedMode greed = GreedMode.AVERAGE;
    private InnerObservable recalculateObservable = new InnerObservable();
    private InnerObservable changeObservable = new InnerObservable();

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
        changeObservable.change();
        changeObservable.notifyObservers();

        recalculateObservable.change();
        recalculateObservable.notifyObservers(getTips());
    }

    public float getTips() {
        return calculate(greed) / persons;
    }

    protected abstract float calculate(GreedMode greed);

    public void deleteObservers() {
        recalculateObservable.deleteObservers();
        changeObservable.deleteObservers();
    }

    public void addCalculateObserver(Observer observer) {
        recalculateObservable.addObserver(observer);
    }

    public void addChangeObserver(Observer observer) {
        changeObservable.addObserver(observer);
    }

    private class InnerObservable extends Observable {
        public void change() {
            setChanged();
        }
    }

}
