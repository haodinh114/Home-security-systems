package sensors;

import model.History;

public class DoorSensor extends SensorController{
    public DoorSensor(String sensorNumber){
        super.sensorName = "Door" + sensorNumber;
        super.sensor_type = SensorType.DOOR;
    }
    @Override
    public String sendMessage(String sensorNumber, String message){
        History history= new History(message, "door");
        String newMessage = history.toString() + ": " + sensorNumber;
        return newMessage;
    }


    /**
     * @param isOpen true if the door is opened
     * @param isArmed true if the system is armed
     */
    public void trigger(Boolean isOpen, Boolean isArmed){
        if (isArmed == isOpen && isArmed == true) {
            super.currentAlert = this.sendMessage(super.sensorName, "Door opened. Alarm trigger. SimpliSafe notified.");
            this.setStatus(SensorStatus.TRIGGERED);
        }
    }
}
