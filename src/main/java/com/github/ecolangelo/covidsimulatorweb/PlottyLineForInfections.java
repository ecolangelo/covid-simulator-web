package com.github.ecolangelo.covidsimulatorweb;

public class PlottyLineForInfections {

    private String[] x;
    private double[] y;



    public PlottyLineForInfections(String[] x, double[] y) {
        this.x = x;
        this.y = y;

    }

    public String[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }


}
