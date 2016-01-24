package com.limitium.smarttipper.core.inflaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.limitium.smarttipper.core.calculation.BaseStrategy;


public abstract class Inflater {

    public void inflateAndSetUp(ViewGroup specContainer,BaseStrategy strategy) {
        View inflate = LayoutInflater.from(specContainer.getContext()).inflate(getTipsLayout(), null);
        specContainer.addView(inflate);
        setup(inflate,strategy);
    }

    protected abstract void setup(View inflate, BaseStrategy strategy);
    protected abstract int getTipsLayout();
}
