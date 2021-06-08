package sensors;

public abstract class SensorController {
    public enum SensorStatus{
        ARMED("ARMED"),
        WAITING("WAITING"),
        TRIGGERED("TRIGGERED");

        String status;
        SensorStatus(String triggered){
            this.status = triggered;
        }
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
    abstract public String sendMessage(String sensorNumber, String message);

    abstract public void trigger(Boolean isOne, Boolean isTwo);



    /**
     * @return current sensor name
     */
    public String getSensorName(){
        return this.sensorName;
    }
}
