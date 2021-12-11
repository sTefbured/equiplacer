package com.kotikov.equiplacer.core.model.dto;

public class EquipmentGraphDTO {
    private int yearsCount;
    private int maxAge;
    private int firstYearAge;
    private int maxNewEquipmentAge;

    public EquipmentGraphDTO(int yearsCount, int maxAge, int firstYearAge, int maxNewEquipmentAge) {
        this.yearsCount = yearsCount;
        this.maxAge = maxAge;
        this.firstYearAge = firstYearAge;
        this.maxNewEquipmentAge = maxNewEquipmentAge;
    }

    public int getYearsCount() {
        return yearsCount;
    }

    public void setYearsCount(int yearsCount) {
        this.yearsCount = yearsCount;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getFirstYearAge() {
        return firstYearAge;
    }

    public void setFirstYearAge(int firstYearAge) {
        this.firstYearAge = firstYearAge;
    }

    public int getMaxNewEquipmentAge() {
        return maxNewEquipmentAge;
    }

    public void setMaxNewEquipmentAge(int maxNewEquipmentAge) {
        this.maxNewEquipmentAge = maxNewEquipmentAge;
    }
}
