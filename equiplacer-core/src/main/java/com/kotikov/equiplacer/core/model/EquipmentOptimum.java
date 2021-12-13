package com.kotikov.equiplacer.core.model;

import com.kotikov.equiplacer.core.model.enums.ReplacementDecision;

import java.util.*;

public class EquipmentOptimum {
    private final List<Map.Entry<ReplacementDecision, EquipmentOptimum>> nextOptimums;

    private int age;
    private double functionValue;

    public EquipmentOptimum() {
        this(0, 0);
    }

    public EquipmentOptimum(double functionValue, int age) {
        this.functionValue = functionValue;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
