package sensors;

public class TemperatureSensor extends SensorController{
    private int temp;
    public TemperatureSensor(String sensorNumber){
        this.temp = 72;
        super.sensorName = "Temperature" + sensorNumber;
        super.sensor_type = SensorType.TEMP;
    }
    public String getSensorNumber() {
        return super.sensorName;
    }
    public void setSensorNumber(String sensorNumber) {
        super.sensorName = sensorNumber;
    }

    public int getTemp(){
        return this.temp;
    }
    public void setTemp(int temp){
        this.temp = temp;
    }

    public String getCurrentTemp(){
        return "The current temperature is " + this.getTemp();
    }

    @Override
    public String sendMessage(String sensorNumber, String message) {
        return "The current temperature is " + this.getTemp();
    }

    public void trigger(Boolean isOne, Boolean isTwo){
        super.currentAlert = this.getCurrentTemp();
    }
}
