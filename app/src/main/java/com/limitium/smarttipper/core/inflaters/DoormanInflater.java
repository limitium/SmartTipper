package com.limitium.smarttipper.core.inflaters;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.DeliveryStrategy;
import com.limitium.smarttipper.core.calculation.DoormanStrategy;

public class DoormanInflater extends Inflater {
    @Override
    protected void setup(View layout, BaseStrategy strategy) {
        final DoormanStrategy realStrategy = (DoormanStrategy) strategy;
        final CheckBox isChristmas = (CheckBox) layout.findViewById(R.id.is_christmas);
        isChristmas.setChecked(realStrategy.isChristmas());
        isChristmas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realStrategy.setIsChristmas(isChecked);
            }
        });
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_doorman;
    }
}
