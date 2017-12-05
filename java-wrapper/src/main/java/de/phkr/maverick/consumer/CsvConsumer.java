package de.phkr.maverick.consumer;

import de.phkr.maverick.TemperatureReading;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 * Created by PHKR on 007 9/7/2016.
 */
public class CsvConsumer implements TemperatureConsumer {
    private static final String SEPARATOR = ";";
    private final File csvFile;

    private static final boolean APPEND = true;

    public CsvConsumer(File csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public void take(TemperatureReading reading) {
        try (FileWriter writer = new FileWriter(csvFile, APPEND)) {
            writer.write(createString(reading) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createString(TemperatureReading reading) {
        Date date = new Date();
        String probe1 = reading.getProbe1()<=0?"":Integer.toString(reading.getProbe1());
        String probe2 = reading.getProbe2()<=0?"":Integer.toString(reading.getProbe2());
        return date.toString() + SEPARATOR + probe1 + SEPARATOR + probe2;
    }
}
