package db;

import model.History;
import model.User;

public class dbtest {
    public static void main(String[] args){
        User user1 = new User("alex2", "alex123",
                "alex@gmail", "Trinh", "alex",
                "1234567", "123 Tacoma");
        UserDb userDb = new UserDb();
//        userDb.createNewDatabase("user.db");
//        userDb.createNewDatabase("history.db");

//        userDb.createUserTable();
//        userDb.addUser(user1);
        HistoryDB historyDB = new HistoryDB();
        historyDB.createHistoryTable();
//        historyDB.addHistory(history1);
        History history1 = new History("House is on fire", "MotionAlert");
//        new History("House1 is on fire", "MotionAlert");
//        new History("House2is on fire", "MotionAlert");
        new History("Smoke detect", "MotionAlert");
        System.out.println(historyDB.selectAll());

//        System.out.println(userDb.getUserPassword(user1.getUsername()));
    }
}
