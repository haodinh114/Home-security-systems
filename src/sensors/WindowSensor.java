package sensors;

import model.History;

public class WindowSensor extends SensorController{
    public WindowSensor(String sensorNumber){
        super.sensor_type = SensorType.WINDOW;
        super.sensorName = "Window" + sensorNumber;
    }
    @Override
    public String sendMessage(String sensorNumber, String message){
        History history= new History(message, "window");
        String newMessage = history.toString() + ": " + sensorNumber;
        return newMessage;
    }


    public void trigger(Boolean isOpen, Boolean isArmed){
        if (isArmed == isOpen && isArmed == true) {
            super.currentAlert = this.sendMessage(super.sensorName, "Window opened. Alarm trigger. SimpliSafe notified.");
            this.setStatus(SensorStatus.TRIGGERED);
        }
    }
}
