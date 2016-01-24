package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.Inflater;
import com.limitium.smarttipper.core.inflaters.TotalInflater;

public class PercentTipStrategy extends BaseStrategy {
    private final float low;
    private final float average;
    private final float high;
    private float sum = 0f;

    public PercentTipStrategy(float low, float average, float high) {
        this.low = low;
        this.average = average;
        this.high = high;
    }

    public void setOrderSumm(float sum) {
        this.sum = sum;
        recalculate();
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

    @Override
    protected float calculate(GreedMode greed) {
        return sum * getPercent(greed) / 100;
    }

    public float getSum() {
        return sum;
    }

    @Override
    public Inflater getInflater() {
        return new TotalInflater();
    }
}
