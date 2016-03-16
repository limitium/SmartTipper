package com.limitium.smarttipper.core;

import android.text.Editable;
import android.widget.TextView;

import com.limitium.smarttipper.core.inflaters.Inflater;

/**
 * Created by limi on 3/8/16.
 */
public class Helper {
    public static float getFloat(Editable text, TextView editor) {
        float total;
        try {
            total = text.length() == 0 ? 0 : Float.valueOf(text.toString());
            if (total < 0) {
                total = 0;
                editor.setText(String.valueOf(total));
            }
        } catch (NumberFormatException e) {
            total = 0;
            editor.setText(String.valueOf(total));
        }

        return total;
    }public static int getInt(Editable text, TextView editor) {
        int total;
        try {
            total = text.length() == 0 ? 0 : Integer.valueOf(text.toString());
            if (total < 0) {
                total = 0;
                editor.setText(String.valueOf(total));
            }
        } catch (NumberFormatException e) {
            total = 0;
            editor.setText(String.valueOf(total));
        }

        return total;
    }
}
