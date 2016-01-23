package com.limitium.smarttipper.core;

import com.limitium.smarttipper.core.tips.Tipable;

public interface TipProvider {
    Tipable getTipStrategy();
}
