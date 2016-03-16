package com.limitium.smarttipper.core.inflaters;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.Helper;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.PercentTipStrategy;

public class TotalInflater extends Inflater {
    protected EditText orderTotal;

    @Override
    protected void setup(View layout, BaseStrategy strategy) {
        final PercentTipStrategy realStrategy = (PercentTipStrategy) strategy;
        orderTotal = (EditText) layout.findViewById(R.id.total_order);
        orderTotal.setText(String.valueOf(realStrategy.getSum()));
        orderTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float total = Helper.getFloat(s, orderTotal);
                realStrategy.setOrderSumm(total);
            }
        });
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_total;
    }
}
