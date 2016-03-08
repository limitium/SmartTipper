package com.limitium.smarttipper.core;

import android.text.Editable;
import android.widget.TextView;

/**
 * Created by limi on 3/8/16.
 */
public class Helper {
    public static float getTotal(Editable text, TextView editor) {
        float total;
        try {
            total = text.length() == 0 ? 0 : Float.valueOf(text.toString());
        } catch (NumberFormatException e) {
            total = 0;
            editor.setText(String.valueOf(total));
        }
        if (total < 0) {
            total = 0;
            editor.setText(String.valueOf(total));
        }
        return total;
    }
}
