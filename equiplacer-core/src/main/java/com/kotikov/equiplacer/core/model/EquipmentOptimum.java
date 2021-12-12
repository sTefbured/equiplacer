package com.kotikov.equiplacer.core.model;

import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.*;

public class EquipmentOptimum {
    private final List<Map.Entry<ReplacementDecision, EquipmentOptimum>> nextOptimums;

    private int year;
    private double functionValue;

    public EquipmentOptimum() {
        this(0, 0);
    }

    public EquipmentOptimum(double functionValue, int year) {
        this.functionValue = functionValue;
        this.year = year;
        nextOptimums = new LinkedList<>();
    }

    public List<Map.Entry<ReplacementDecision, EquipmentOptimum>> getNextOptimums() {
        return nextOptimums;
    }

    public double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(double functionValue) {
        this.functionValue = functionValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
