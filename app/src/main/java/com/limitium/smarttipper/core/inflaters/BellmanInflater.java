package com.limitium.smarttipper.core.inflaters;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.calculation.BartenderStrategy;
import com.limitium.smarttipper.core.calculation.BaseStrategy;
import com.limitium.smarttipper.core.calculation.BellmanStrategy;

public class BellmanInflater extends Inflater {

    @Override
    protected void setup(final View layout, BaseStrategy strategy) {
        final BellmanStrategy realStrategy = (BellmanStrategy) strategy;

        final EditText totalbags = (EditText) layout.findViewById(R.id.total_bags);
        totalbags.setText(String.valueOf(realStrategy.getBags()));
        totalbags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = s.length() == 0 ? 0 : Integer.valueOf(s.toString());
                if (total < 0) {
                    total = 0;
                    totalbags.setText(String.valueOf(total));
                }
                realStrategy.setBags(total);
            }
        });
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_bellman;
    }
}
