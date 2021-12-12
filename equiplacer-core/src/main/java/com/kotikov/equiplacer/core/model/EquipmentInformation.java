package com.kotikov.equiplacer.core.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

//TODO: find out if should delete equipmentCosts (maybe residual costs is enough)
public class EquipmentInformation {
    private int maxAge;
    private int yearsCount;
    private int currentAge;
    private int maxNewEquipmentAge;
    private boolean isSellLastYearEquipmentOn;
    private List<Integer> equipmentCosts;
    private List<Integer> maintenanceCosts;
    private List<Integer> residualCosts;
    private List<Integer> incomes;

    public EquipmentInformation() {

    }

    public EquipmentInformation(int maxAge, int yearsCount, int currentAge, int maxNewEquipmentAge,
                                List<Integer> equipmentCosts, List<Integer> maintenanceCosts,
                                List<Integer> residualCosts, List<Integer> incomes) {
        this.maxAge = maxAge;
        this.yearsCount = yearsCount;
        this.currentAge = currentAge;
        this.maxNewEquipmentAge = maxNewEquipmentAge;
        this.equipmentCosts = equipmentCosts;
        this.maintenanceCosts = maintenanceCosts;
        this.residualCosts = residualCosts;
        this.incomes = incomes;
    }

    public static EquipmentInformationBuilder builder() {
        return new EquipmentInformationBuilder();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof EquipmentInformation that)) {
            return false;
        }

        return new EqualsBuilder().append(maxAge, that.maxAge).append(yearsCount, that.yearsCount)
                .append(currentAge, that.currentAge).append(maxNewEquipmentAge, that.maxNewEquipmentAge)
                .append(equipmentCosts, that.equipmentCosts).append(maintenanceCosts, that.maintenanceCosts)
                .append(residualCosts, that.residualCosts).append(incomes, that.incomes).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(maxAge).append(yearsCount)
                .append(currentAge).append(maxNewEquipmentAge).append(equipmentCosts).append(maintenanceCosts)
                .append(residualCosts).append(incomes).toHashCode();
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getYearsCount() {
        return yearsCount;
    }

    public void setYearsCount(int yearsCount) {
        this.yearsCount = yearsCount;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public List<Integer> getEquipmentCosts() {
        return equipmentCosts;
    }

    public void setEquipmentCosts(List<Integer> equipmentCosts) {
        this.equipmentCosts = equipmentCosts;
    }

    public List<Integer> getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(List<Integer> maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    public List<Integer> getResidualCosts() {
        return residualCosts;
    }

    public void setResidualCosts(List<Integer> residualCosts) {
        this.residualCosts = residualCosts;
    }

    public List<Integer> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Integer> incomes) {
        this.incomes = incomes;
    }

    public int getMaxNewEquipmentAge() {
        return maxNewEquipmentAge;
    }

    public void setMaxNewEquipmentAge(int maxNewEquipmentAge) {
        this.maxNewEquipmentAge = maxNewEquipmentAge;
    }

    public boolean isSellLastYearEquipmentOn() {
        return isSellLastYearEquipmentOn;
    }

    public void setSellLastYearEquipmentOn(boolean sellLastYearEquipmentOn) {
        isSellLastYearEquipmentOn = sellLastYearEquipmentOn;
    }

    public static class EquipmentInformationBuilder {
        private int maxAge;
        private int yearsCount;
        private int currentAge;
        private int maxNewEquipmentAge;
        private boolean isSellLastYearEquipmentOn;
        private List<Integer> equipmentCosts;
        private List<Integer> maintenanceCosts;
        private List<Integer> residualCosts;
        private List<Integer> incomes;

        private EquipmentInformationBuilder() {
        }

        public EquipmentInformation build() {
            return new EquipmentInformation(maxAge, yearsCount, currentAge, maxNewEquipmentAge,
                    equipmentCosts, maintenanceCosts, residualCosts, incomes);
        }

        public int getMaxAge() {
            return maxAge;
        }

        public EquipmentInformationBuilder setMaxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public int getYearsCount() {
            return yearsCount;
        }

        public EquipmentInformationBuilder setYearsCount(int yearsCount) {
            this.yearsCount = yearsCount;
            return this;
        }

        public int getCurrentAge() {
            return currentAge;
        }

        public EquipmentInformationBuilder setCurrentAge(int currentAge) {
            this.currentAge = currentAge;
            return this;
        }

        public int getMaxNewEquipmentAge() {
            return maxNewEquipmentAge;
        }

        public EquipmentInformationBuilder setMaxNewEquipmentAge(int maxNewEquipmentAge) {
            this.maxNewEquipmentAge = maxNewEquipmentAge;
            return this;
        }

        public List<Integer> getEquipmentCosts() {
            return equipmentCosts;
        }

        public EquipmentInformationBuilder setEquipmentCosts(List<Integer> equipmentCosts) {
            this.equipmentCosts = equipmentCosts;
            return this;
        }

        public List<Integer> getMaintenanceCosts() {
            return maintenanceCosts;
        }

        public EquipmentInformationBuilder setMaintenanceCosts(List<Integer> maintenanceCosts) {
            this.maintenanceCosts = maintenanceCosts;
            return this;
        }

        public List<Integer> getResidualCosts() {
            return residualCosts;
        }

        public EquipmentInformationBuilder setResidualCosts(List<Integer> residualCosts) {
            this.residualCosts = residualCosts;
            return this;
        }

        public List<Integer> getIncomes() {
            return incomes;
        }

        public EquipmentInformationBuilder setIncomes(List<Integer> incomes) {
            this.incomes = incomes;
            return this;
        }

        public boolean isSellLastYearEquipmentOn() {
            return isSellLastYearEquipmentOn;
        }

        public void setSellLastYearEquipmentOn(boolean sellLastYearEquipmentOn) {
            isSellLastYearEquipmentOn = sellLastYearEquipmentOn;
        }
    }
}
