package model;

import sensors.SensorController;

public class TriggerSimulator {
    private SystemController mainSystem;
    public TriggerSimulator(SystemController system){
        this.mainSystem = system;
    }

    public void triggerSensor(SensorController sensor){
        sensor.trigger(true, true);
    }
}
