package sensors;

import model.History;

public class SmokeSensor extends SensorController{
    public SmokeSensor(String sensorNumber){
        super.sensorName = "Smoke" + sensorNumber;
        super.sensor_type = SensorType.SMOKE;
    }
    @Override
    public String sendMessage(String sensorNumber, String message){
        History history= new History(message, "smoke");
        String newMessage = history.toString() + ": " +  sensorNumber;
        return newMessage;
    }


    public void trigger(Boolean isOne, Boolean isTwo){
        super.currentAlert = this.sendMessage(super.sensorName,"Smoke detected. Alarm trigger. SimpliSafe notified.");
        this.setStatus(SensorController.SensorStatus.TRIGGERED);
    }
}
