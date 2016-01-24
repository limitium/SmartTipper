package com.limitium.smarttipper.core.calculation;


import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.EmptyInflater;
import com.limitium.smarttipper.core.inflaters.Inflater;

public class FixTipStrategy extends BaseStrategy {
    private final float low;
    private final float average;
    private final float high;

    public FixTipStrategy(float low, float average, float high) {
        this.low = low;
        this.average = average;
        this.high = high;
    }

    @Override
    protected float calculate(GreedMode greed) {
        if (greed == GreedMode.LOW) {
            return low;
        }
        if (greed == GreedMode.AVERAGE) {
            return average;
        }
        return high;
    }

    @Override
    public Inflater getInflater() {
        return new EmptyInflater();
    }
}
