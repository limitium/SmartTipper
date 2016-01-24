package com.limitium.smarttipper.core.calculation;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.Inflater;
import com.limitium.smarttipper.core.inflaters.TakeoutInflater;
import com.limitium.smarttipper.core.inflaters.TotalInflater;

public class TakeoutStrategy extends PercentTipStrategy {
    private final float percent;

    public TakeoutStrategy(float low, float average, float percent) {
        super(low, average, 0);
        this.percent = percent;
    }

    @Override
    protected float calculate(GreedMode greed) {
        if (greed == GreedMode.LOW) {
            return low;
        }
        if (greed == GreedMode.AVERAGE) {
            return average;
        }
        return sum * percent / 100;
    }

    @Override
    public Inflater getInflater() {
        return new TakeoutInflater();
    }
}
