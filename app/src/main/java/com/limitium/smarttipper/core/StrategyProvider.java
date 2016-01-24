package com.limitium.smarttipper.core;

import com.limitium.smarttipper.core.calculation.BaseStrategy;

public interface StrategyProvider {
    BaseStrategy getTipStrategy();
}
