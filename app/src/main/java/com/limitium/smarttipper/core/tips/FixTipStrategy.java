package com.limitium.smarttipper.core.tips;


import com.limitium.smarttipper.core.GreedMode;

public class FixTipStrategy implements Tipable {
    private final float low;
    private final float average;
    private final float high;

    public FixTipStrategy(float low, float average, float high) {
        this.low = low;
        this.average = average;
        this.high = high;
    }

    @Override
    public float getTips(GreedMode greed) {
        if (greed == GreedMode.LOW) {
            return low;
        }
        if (greed == GreedMode.AVERAGE) {
            return average;
        }
        return high;
    }
}
