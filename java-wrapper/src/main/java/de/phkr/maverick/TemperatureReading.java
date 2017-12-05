package de.phkr.maverick;

/**
 * Created by PHKR on 007 9/7/2016.
 */
public class TemperatureReading {
    private final int probe1;
    private final int probe2;

    public TemperatureReading(int probe1, int probe2) {
        this.probe1 = probe1;
        this.probe2 = probe2;
    }

    public int getProbe1() {
        return probe1;
    }

    public int getProbe2() {
        return probe2;
    }
}
