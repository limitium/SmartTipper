package com.limitium.smarttipper.core.inflaters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.DeliveryStrategy;

public class DeliveryInflater extends Inflater {
    @Override
    protected void setup(View layout, BaseStrategy strategy) {
        final DeliveryStrategy realStrategy = (DeliveryStrategy) strategy;
        final CheckBox isBadWeather = (CheckBox) layout.findViewById(R.id.is_bad_weather);
        isBadWeather.setChecked(realStrategy.isBadWeather());
        isBadWeather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realStrategy.setIsBadWeather(isChecked);
            }
        });
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_delivery;
    }
}
