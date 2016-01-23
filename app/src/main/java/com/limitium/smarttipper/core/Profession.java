package com.limitium.smarttipper.core;

import com.limitium.smarttipper.core.tips.Tipable;

public class Profession implements TipProvider {
    private final Integer icon;
    private final Integer name;
    private final Integer color;

    private final Integer percentOfSalary;
    private final Integer percentOfPayers;
    private final Tipable tipStrategy;


    public Profession(Integer icon, Integer name,Integer color, Integer percentOfSalary, Integer percentOfPayers, Tipable tipStrategy) {
        this.icon = icon;
        this.name = name;
        this.color=color;
        this.percentOfSalary = percentOfSalary;
        this.percentOfPayers = percentOfPayers;
        this.tipStrategy = tipStrategy;
    }

    public Integer getIcon() {
        return icon;
    }


    public Integer getName() {
        return name;
    }


    public Integer getPercentOfSalary() {
        return percentOfSalary;
    }

    public Integer getPercentOfPayers() {
        return percentOfPayers;
    }

    @Override
    public Tipable getTipStrategy() {
        return tipStrategy;
    }

    public Integer getColor() {
        return color;
    }
}
