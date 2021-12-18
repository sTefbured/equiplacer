package com.kotikov.equiplacer.client.desktop.model;

import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.ArrayList;
import java.util.List;

public class SolutionStageRow {
    private final List<ReplacementDecision> replacementDecision;

    private int age;
    private double saveFunctionValue;
    private List<Double> replaceFunctionValues;
    private double maxFunctionValue;

    public SolutionStageRow(int age, double saveFunctionValue, List<Double> replaceFunctionValues) {
        this.age = age;
        this.saveFunctionValue = saveFunctionValue;
        this.replaceFunctionValues = replaceFunctionValues;
        replacementDecision = new ArrayList<>();
        maxFunctionValue = saveFunctionValue;
        for (var value : replaceFunctionValues) {
            if (value > maxFunctionValue) {
                maxFunctionValue = value;
            }
        }
        if (maxFunctionValue == saveFunctionValue) {
            replacementDecision.add(ReplacementDecision.KEEP);
        }
        for (var value : replaceFunctionValues) {
            if (value == maxFunctionValue) {
                replacementDecision.add(ReplacementDecision.REPLACE);
                break;
            }
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSaveFunctionValue() {
        return saveFunctionValue;
    }

    public void setSaveFunctionValue(double saveFunctionValue) {
        this.saveFunctionValue = saveFunctionValue;
    }

    public List<Double> getReplaceFunctionValues() {
        return replaceFunctionValues;
    }

    public void setReplaceFunctionValues(List<Double> replaceFunctionValues) {
        this.replaceFunctionValues = replaceFunctionValues;
    }

    public double getMaxFunctionValue() {
        return maxFunctionValue;
    }

    public List<ReplacementDecision> getReplacementDecision() {
        return replacementDecision;
    }
}
