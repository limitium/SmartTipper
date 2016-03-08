package com.limitium.smarttipper.core.inflaters;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.limitium.smarttipper.R;
import com.limitium.smarttipper.core.Helper;
import com.limitium.smarttipper.core.calculation.BartenderStrategy;
import com.limitium.smarttipper.core.calculation.BaseStrategy;

public class BartenderInflater extends TotalInflater {

    @Override
    protected void setup(final View layout, BaseStrategy strategy) {
        super.setup(layout, strategy);
        final BartenderStrategy realStrategy = (BartenderStrategy) strategy;
        final CheckBox isBadWeather = (CheckBox) layout.findViewById(R.id.is_large_tab);
        isBadWeather.setChecked(realStrategy.isLargeTab());
        isBadWeather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realStrategy.setIsLargeTab(isChecked);
                changeLargeView(isChecked, layout);
            }
        });
        changeLargeView(isBadWeather.isChecked(), layout);

        final EditText totalBeers = (EditText) layout.findViewById(R.id.total_beers);
        totalBeers.setText(String.valueOf(realStrategy.getBeers()));
        totalBeers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float total = Helper.getTotal(s, totalBeers);
                realStrategy.setBeers((int) total);
            }
        });

        final EditText totalCocktails = (EditText) layout.findViewById(R.id.total_cocktails);
        totalCocktails.setText(String.valueOf(realStrategy.getCocktails()));
        totalCocktails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float total = Helper.getTotal(s, totalCocktails);
                realStrategy.setCocktails((int) total);
            }
        });
    }

    private void changeLargeView(boolean isChecked, View layout) {
        layout.findViewById(R.id.total_container).setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
        layout.findViewById(R.id.total_drinks).setVisibility(!isChecked ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected int getTipsLayout() {
        return R.layout.tips_bartender;
    }
}
