package model;
import db.HistoryDB;
import db.UserDb;
import sensors.*;
import sensors.SensorController.*;

import java.util.ArrayList;

public class SystemController {
    private Boolean isValid;
    private String pinCode;
    private SensorController.SensorStatus status;
    private ArrayList<SensorController> sensorList;
    HistoryDB historyDB;
    UserDb userDb;

    /**
     * There will be 14 sensors: 2 DoorSensors, 2 WindowSensors, 4 MotionSensors, 4 TempSensors, 4Smoke Sensor
     *
     * System Status: ARMED, WAITING, TRIGGERED
     *
     * Use getListSensors() to get list of the sensor
     *
     * Use select10RecentRecords() or selectAllRecords() to get a list of history
     *
     * Use trigger(SensorIndex) to trigger the sensor -> return a Notification
     *
     *
     */
    public SystemController(){
        sensorList = new ArrayList<>();
        isValid = false;
        this.setUp();
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
     *               To change the status, call the checkPin method, if it is true, you can change the status
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
    public String selectAllRecords(){
        return this.historyDB.selectAll();
    }

    public String select10RecentRecords(){
        return this.historyDB.select10Records();
    }


    /**
     * Setup the main system
     */
    private void setUp(){
        userDb = new UserDb();
        userDb.createNewDatabase("user.db");
        userDb.createNewDatabase("history.db");
        userDb.createUserTable();
        historyDB = new HistoryDB();
        historyDB.createHistoryTable();
        this.addSensor(new DoorSensor("1"));
        this.addSensor(new DoorSensor("2"));

        this.addSensor(new WindowSensor("1"));
        this.addSensor(new WindowSensor("2"));

        this.addSensor(new MotionSensor("1"));
        this.addSensor(new MotionSensor("2"));
        this.addSensor(new MotionSensor("3"));
        this.addSensor(new MotionSensor("4"));

        this.addSensor(new TemperatureSensor("1"));
        this.addSensor(new TemperatureSensor("2"));
        this.addSensor(new TemperatureSensor("3"));
        this.addSensor(new TemperatureSensor("4"));

        this.addSensor(new SmokeSensor("1"));
        this.addSensor(new SmokeSensor("2"));
        this.addSensor(new SmokeSensor("3"));
        this.addSensor(new SmokeSensor("4"));

        this.status = SensorStatus.WAITING;
    }

    /**
     * @param sensorIndex Sensor index is from 0 to 13
     * @return the alert of selected sensor.
     */
    public String trigger(int sensorIndex){
        SensorController currentSensor = this.getSensor(sensorIndex);
        this.setStatus(SensorStatus.ARMED);
        currentSensor.trigger(true, this.getStatus() == SensorStatus.ARMED);
        return currentSensor.getCurrentAlert();
    }

    /**
     * @return get current system status
     */
    public String getCurrentStatus(){
        return this.status.toString();
    }

}
