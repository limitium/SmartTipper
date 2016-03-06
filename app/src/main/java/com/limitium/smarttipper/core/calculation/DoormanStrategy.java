package com.limitium.smarttipper.core.calculation;


import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.DoormanInflater;
import com.limitium.smarttipper.core.inflaters.EmptyInflater;
import com.limitium.smarttipper.core.inflaters.Inflater;

public class DoormanStrategy extends FixTipStrategy {

    private boolean isChristmas;
    public DoormanStrategy(float low, float average, float high) {
        super(low,average,high);
    }

    public boolean isChristmas() {
        return isChristmas;
    }

    public void setIsChristmas(boolean isChristmas) {
        this.isChristmas = isChristmas;
        recalculate();
    }

    @Override
    protected float calculate(GreedMode greed) {
        if(!isChristmas){
            return 0;
        }
        return super.calculate(greed);
    }

    @Override
    public Inflater getInflater() {
        return new DoormanInflater();
    }
}
