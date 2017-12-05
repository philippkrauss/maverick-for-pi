package de.phkr.maverick.consumer;

import de.phkr.maverick.TemperatureReading;

/**
 * Created by PHKR on 007 9/7/2016.
 */
public interface TemperatureConsumer {

    void take(TemperatureReading reading);

}
