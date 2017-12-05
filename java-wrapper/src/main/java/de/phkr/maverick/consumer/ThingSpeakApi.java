package de.phkr.maverick.consumer;

import de.phkr.maverick.TemperatureReading;
import de.phkr.maverick.consumer.TemperatureConsumer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by PHKR on 007 9/7/2016.
 */
public class ThingSpeakApi implements TemperatureConsumer {

    private static final String BASE_URL = "https://api.thingspeak.com";
    private final String apiKey;

    public ThingSpeakApi(String apiKey) {
        this.apiKey = apiKey;
    }

    private void read_content(HttpsURLConnection con) {
        if (con != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((br.readLine()) != null) {
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendData(int probe1, int probe2) {
        if (probe1 < 0 && probe2 < 0) {
            return;
        }
        String https_url = BASE_URL + "/update?api_key=" + apiKey + "&";
        if (probe1 >= 0) {
            https_url = https_url + "field1=" + probe1;
        }
        if (probe1 >= 0 && probe2 >= 0) {
            https_url = https_url + "&";
        }
        if (probe2 >= 0) {
            https_url = https_url + "field2=" + probe2;
        }
        try {
            URL url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            //dump all the content
            read_content(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void take(TemperatureReading reading) {
        sendData(reading.getProbe1(), reading.getProbe2());
    }

}
