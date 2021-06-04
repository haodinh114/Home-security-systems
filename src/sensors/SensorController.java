package sensors;

public abstract class SensorController {
    public enum SensorStatus{
        ARMED,
        WAITING,
        TRIGGERED
    }

    enum SensorType{
        MOTION,
        SMOKE,
        TEMP,
        DOOR,
        WINDOW
    }
    SensorType sensor_type;
    SensorStatus status;
    String sensorName;
    public SensorController(){
        this.status = SensorStatus.WAITING;
        this.sensorName = "";
        this.currentAlert = "";
    }
    public SensorType getSensor_type() {
        return sensor_type;
    }

    protected String currentAlert;

    public String getCurrentAlert() {
        return currentAlert;
    }

    /**
     * @return get sensor current status
     */
    public SensorStatus getStatus() {
        return status;
    }

    /**
     * @param status change the sensor status
     */
    public void setStatus(SensorStatus status) {
        this.status = status;
    }

    /**
     * @return send a suitable message depend on the sensor type
     */
    public String sendMessage() {
        return null;
    }

    public void trigger(Boolean isOne, Boolean isTwo){};

    /**
     * @return current sensor name
     */
    public String getSensorName(){
        return this.sensorName;
    }
}
