package com.kotikov.equiplacer.client.desktop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionStage {
    private final int year;
    private final List<SolutionStageRow> rows;

    public SolutionStage(int year) {
        this.year = year;
        rows = new ArrayList<>();
    }

    public void addRow(SolutionStageRow row) {
        rows.add(row);
    }

    public int getYear() {
        return year;
    }

    public List<SolutionStageRow> getRows() {
        return Collections.unmodifiableList(rows);
    }
}
