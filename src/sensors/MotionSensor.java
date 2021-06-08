package sensors;

import model.History;

public class MotionSensor extends SensorController{
    public MotionSensor(String sensorNumber){
        super.sensor_type = SensorType.MOTION;
        super.sensorName = "Motion" + sensorNumber;
    }
    @Override
    public String sendMessage(String sensorNumber, String message){
        History history= new History(message, "motion");
        String newMessage = history.toString() + ": " + sensorNumber;
        return newMessage;
    }


    public void trigger(Boolean isDetected, Boolean isArmed){
        if (isDetected == isArmed && isDetected == true) {
            super.currentAlert = this.sendMessage(super.sensorName, "Motion detected. Alarm trigger. SimpliSafe notified.");
            this.setStatus(SensorStatus.TRIGGERED);
        }
    }

}
