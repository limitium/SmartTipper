package com.limitium.smarttipper.core.inflaters;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.PercentTipStrategy;
import com.limitium.smarttipper.core.calculation.TakeoutStrategy;

import java.util.Observable;
import java.util.Observer;

public class TakeoutInflater extends TotalInflater {

    @Override
    protected void setup(final View layout, BaseStrategy strategy) {
        super.setup(layout, strategy);
        final TakeoutStrategy realStrategy = (TakeoutStrategy) strategy;
        realStrategy.addChangeObserver(new Observer() {
            @Override
            public void update(Observable observable, Object data) {
                layout.findViewById(R.id.total_container).setVisibility(realStrategy.getGreed() == GreedMode.HIGH ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_total;
    }
}
