package com.limitium.smarttipper.core.tips;

import com.limitium.smarttipper.core.GreedMode;

public interface Tipable {
    float getTips(GreedMode greed);
}
