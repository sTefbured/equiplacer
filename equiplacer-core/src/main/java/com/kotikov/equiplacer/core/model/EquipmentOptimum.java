package com.kotikov.equiplacer.core.model;

import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EquipmentOptimum {
    private final List<EquipmentOptimum> nextOptimums;

    private double functionValue;
    private ReplacementDecision replacementDecision;
    private final List<Integer> newEquipmentAges;

    public EquipmentOptimum() {
        this(0, null);
    }

    public EquipmentOptimum(double functionValue, ReplacementDecision replacementDecision) {
        this.functionValue = functionValue;
        this.replacementDecision = replacementDecision;
        newEquipmentAges = new LinkedList<>();
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

    public List<Integer> getNewEquipmentAges() {
        return Collections.unmodifiableList(newEquipmentAges);
    }

    public void addNewEquipmentAges(List<Integer> newEquipmentAges) {
        this.newEquipmentAges.addAll(newEquipmentAges);
    }
}
