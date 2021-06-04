package model;
import db.HistoryDB;
import sensors.SensorController;
import sensors.SensorController.*;

import java.util.ArrayList;

public class SystemController {
    private Boolean isValid;
    private String pinCode;
    private SensorController.SensorStatus status;
    private ArrayList<SensorController> sensorList;

    public SystemController(){
        sensorList = new ArrayList<>();
        isValid = false;
    }

    /**
     * @param newPin change system pincode
     */
    public void setPinCode(String newPin){
        this.pinCode = newPin;
    }

    /**
     * @return System pincode
     */
    public String getPinCode(){
        return this.pinCode;
    }

    /**
     * @return current system status
     */
    public SensorStatus getStatus() {
        return status;
    }

    /**
     * @param status change the system status (ARMED/WAITING/TRIGGERED)
     */
    public void setStatus(SensorStatus status) {
        this.status = status;
    }

    /**
     * @param sensor new sensor to add
     */
    public void addSensor(SensorController sensor){
        this.sensorList.add(sensor);
    }

    /**
     * @return List of the sensors inside system.
     */
    public String getListSensors(){
        StringBuilder listofSensors = new StringBuilder();
        for (int i = 0; i < this.sensorList.size(); i++){
            listofSensors.append(this.sensorList.get(i).getSensorName() + " at index "+ i + "\n" );
        }
        return listofSensors.toString();
    }

    /**
     * @param index index of the sensor from the list
     * @return the sensor at that index
     */
    public SensorController getSensor(int index){
        return this.sensorList.get(index);
    }

    /**
     * @param input: input pincode
     * @return true if the pin is correct
     */
    public Boolean checkPin(String input){
        this.isValid = this.pinCode.equals(input);
        return this.isValid;
    }

    /**
     * @return all history of system from database
     */
    public String displayHistory(){
        HistoryDB history = new HistoryDB();
        return history.selectAll();
    }
}
