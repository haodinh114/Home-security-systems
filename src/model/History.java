package model;
import java.time.LocalDate;
import java.time.LocalTime;
public class History {

    String time;
    String date;
    String content;
    String username;
    public History(String content, String username){
        this.content = content;
        this.time = LocalTime.now().toString();
        this.date = LocalDate.now().toString();
        this.username = username;
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

    @Override
    public String toString() {
        return "History{" +
                "time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getFields(){
        return "date, time, content, username";
    }

    public String getValues(){
        return String.format("'%s', '%s', '%s', '%s'", this.date,this.time, this.content, this.username);
    }


}
