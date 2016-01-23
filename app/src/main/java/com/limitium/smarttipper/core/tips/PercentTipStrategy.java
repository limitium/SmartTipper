package com.limitium.smarttipper.core.tips;

import com.limitium.smarttipper.core.GreedMode;

public class PercentTipStrategy implements Tipable {
    private final float low;
    private final float average;
    private final float high;
    private float sum;

    public PercentTipStrategy(float low, float average, float high) {
        this.low = low;
        this.average = average;
        this.high = high;
    }

    @Override
    public float getTips(GreedMode greed) {
        return sum * getPercent(greed) / 100;
    }

    public void setOrderSumm(float sum) {
        this.sum = sum;
    }

    private float getPercent(GreedMode greed) {
        if (greed == GreedMode.LOW) {
            return low;
        }
        if (greed == GreedMode.AVERAGE) {
            return average;
        }
        return high;
    }
}
