package de.phkr.maverick.config;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private String nativeCommand = "";
    private String apiKey = "";
    private String path = "";

    public String getNativeCommand() {
        return nativeCommand;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getPath() {
        return path;
    }

    public void init() throws Exception {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(in);
            apiKey = props.getProperty("thingspeak.api.key");
            path = props.getProperty("csv.path");
            nativeCommand = props.getProperty("maverick.command");
        }
    }
}
