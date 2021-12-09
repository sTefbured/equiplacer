package com.kotikov.equiplacer.core.model.dto;

import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.ArrayList;
import java.util.List;

public class EquipmentOptimum {
    private final List<EquipmentOptimum> nextOptimums;

    private double functionValue;
    private ReplacementDecision replacementDecision;

    public EquipmentOptimum() {
        this(0, null);
    }

    public EquipmentOptimum(double functionValue, ReplacementDecision replacementDecision) {
        this.functionValue = functionValue;
        this.replacementDecision = replacementDecision;
        nextOptimums = new ArrayList<>(2);
    }

    public List<EquipmentOptimum> getNextOptimums() {
        return nextOptimums;
    }

    public double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(double functionValue) {
        this.functionValue = functionValue;
    }

    public ReplacementDecision getReplacementDecision() {
        return replacementDecision;
    }

    public void setReplacementDecision(ReplacementDecision replacementDecision) {
        this.replacementDecision = replacementDecision;
    }
}
