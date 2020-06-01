package com.github.ecolangelo.covidsimulatorweb;

public class SimulatorData {

    private PlottyLineForInfections plottyLineForInfections;


    private String lastUpdated;

    private String estimatedDayOfEnding;

    public PlottyLineForInfections getPlottyLineForInfections() {
        return plottyLineForInfections;
    }

    public void setPlottyLineForInfections(PlottyLineForInfections plottyLineForInfections) {
        this.plottyLineForInfections = plottyLineForInfections;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getEstimatedDayOfEnding() {
        return estimatedDayOfEnding;
    }

    public void setEstimatedDayOfEnding(String estimatedDayOfEnding) {
        this.estimatedDayOfEnding = estimatedDayOfEnding;
    }
}
