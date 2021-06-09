package model;
import db.HistoryDB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class History {

    String time;
    String date;
    String content;
    String type_alert;
    int isResolved;
    public History(String content, String type_alert){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.content = content;
        this.time = String.valueOf(LocalTime.now().getHour()) + ":" + String.valueOf(LocalTime.now().getMinute()) + ":" + String.valueOf(LocalTime.now().getSecond());
        this.date = LocalDate.now().toString();
        this.type_alert = type_alert;
        this.isResolved = 0;
        new HistoryDB().addHistory(this);
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsResolved(int value){
        this.isResolved = value;
    }

    @Override
    public String toString() {
        return  "Date= " + date +
                ", Time=" + time +
                ", Message= " + content +
                ", Sensor= " + type_alert;
    }

    public String getFields(){
        return "date, time, content, type_alert, isResolved";
    }

    public String getValues(){
        return String.format("'%s', '%s', '%s', '%s', '%s'", this.date,this.time, this.content, this.type_alert, this.isResolved);
    }


}
