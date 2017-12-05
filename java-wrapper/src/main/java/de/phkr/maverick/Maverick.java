package de.phkr.maverick;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bounty
 */
public class Maverick {

    private final String nativeCommand;

    public Maverick(String nativeCommand) {
        this.nativeCommand = nativeCommand;
    }

    public TemperatureReading getReading() {
        int probe1 = -1;
        int probe2 = -1;
        try {
            System.out.println("Executing " + nativeCommand);
            Process proc = Runtime.getRuntime().exec(nativeCommand);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s;
            String probeString = "";
            while ((s = stdInput.readLine()) != null) {
                probeString = s;
            }
            System.out.println("Read: " + probeString);
            Pattern pattern = Pattern.compile("Probe 1:(-?[0-9]+).+Probe 2:(-?[0-9]+)");
            Matcher matcher = pattern.matcher(probeString);

            String probe1String = "-1";
            String probe2String = "-1";
            if (matcher.find()) {
                probe1String = matcher.group(1);
                probe2String = matcher.group(2);
            }
            probe1 = Integer.parseInt(probe1String);
            probe2 = Integer.parseInt(probe2String);

            System.out.println("probe1: " + probe1 + ", probe2: " + probe2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TemperatureReading(probe1, probe2);
    }

}

