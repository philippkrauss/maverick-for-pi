package de.phkr.maverick;

import de.phkr.maverick.consumer.CsvConsumer;
import de.phkr.maverick.consumer.ThingSpeakApi;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public void execute() throws Exception {
        String apiKey = "";
        String path = "";
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);
            apiKey = props.getProperty("thingspeak.api.key");
            path = props.getProperty("csv.path");
        }

        Maverick maverick = new Maverick();
        ThingSpeakApi thingSpeakApi = new ThingSpeakApi(apiKey);
        CsvConsumer csvConsumer = new CsvConsumer(new File(path));
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
