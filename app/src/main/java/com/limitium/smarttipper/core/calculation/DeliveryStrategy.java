package com.limitium.smarttipper.core.calculation;


import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.inflaters.Inflater;
import com.limitium.smarttipper.core.inflaters.DeliveryInflater;

public class DeliveryStrategy extends FixTipStrategy {
    private static final float extra = 1.5f;
    private boolean isBadWeather = false;

    public DeliveryStrategy(float low, float average, float high) {
        super(low, average, high);
    }

    public boolean isBadWeather() {
        return isBadWeather;
    }

    public void setIsBadWeather(boolean isBadWeather) {
        this.isBadWeather = isBadWeather;
        recalculate();
    }


    @Override
    protected float calculate(GreedMode greed) {
        float total = super.calculate(greed);
        return isBadWeather ? total * extra : total;
    }

    @Override
    public Inflater getInflater() {
        return new DeliveryInflater();
    }
}
