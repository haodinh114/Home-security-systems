package db;

import model.History;
import model.User;

public class dbtest {
    public static void main(String[] args){
        User user1 = new User("alex2", "alex123",
                "alex@gmail", "Trinh", "alex",
                "1234567");
        UserDb userDb = new UserDb();
        userDb.createNewDatabase("user.db");
        userDb.createNewDatabase("history.db");

        userDb.createUserTable();
        userDb.addUser(user1);
        History history1 = new History("House is on fire", user1.getUsername());
        HistoryDB historyDB = new HistoryDB();
//        historyDB.createHistoryTable();
//        historyDB.addHistory(history1);
        historyDB.selectAll(user1.getUsername());

        System.out.println(userDb.getUserPassword(user1.getUsername()));
    }
}
