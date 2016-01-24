package com.limitium.smarttipper.core.inflaters;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.PercentTipStrategy;

public class EmptyInflater extends Inflater {

    @Override
    protected void setup(View layout, BaseStrategy strategy) {
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_empty;
    }
}
