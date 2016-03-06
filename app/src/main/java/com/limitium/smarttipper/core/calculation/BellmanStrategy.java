package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.BartenderInflater;
import com.limitium.smarttipper.core.inflaters.BellmanInflater;
import com.limitium.smarttipper.core.inflaters.Inflater;

public class BellmanStrategy extends BaseStrategy {
    protected final float low;
    protected final float average;
    protected final float high;
    private int bags;

    public BellmanStrategy(float low, float average, float high) {
        this.low = low;
        this.average = average;
        this.high = high;

    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
        recalculate();
    }

    @Override
    protected float calculate(GreedMode greed) {
        if (greed == GreedMode.LOW) {
            return low;
        }
        if (greed == GreedMode.AVERAGE) {
            return Math.max(bags * average, high);
        }
        return bags * high;
    }

    @Override
    public Inflater getInflater() {
        return new BellmanInflater();
    }

}
