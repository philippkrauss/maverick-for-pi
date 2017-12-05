package de.phkr.maverick;

import de.phkr.maverick.config.Configuration;
import de.phkr.maverick.consumer.CsvConsumer;
import de.phkr.maverick.consumer.ThingSpeakApi;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public void execute() throws Exception {
        Configuration configuration = new Configuration();
        configuration.init();

        Maverick maverick = new Maverick(configuration.getNativeCommand());
        ThingSpeakApi thingSpeakApi = new ThingSpeakApi(configuration.getApiKey());
        CsvConsumer csvConsumer = new CsvConsumer(new File(configuration.getPath()));
        while (true) {
            TemperatureReading reading = maverick.getReading();
            thingSpeakApi.take(reading);
            csvConsumer.take(reading);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().execute();
    }
}
