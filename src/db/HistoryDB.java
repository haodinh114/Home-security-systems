package db;

import java.sql.*;

import model.History;
public class HistoryDB {
    public void createHistoryTable(){
        {
            try
            {
                Connection connection = this.connect();
                // create a database connection
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS history " +
                        "(id integer primary key autoincrement, " +
                        "date text not null, time text not null, " +
                        "content text not null, type_alert text not null)");
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

    public void addHistory(History history){
        try
        {
            Connection connection = this.connect();
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(String.format("insert into history(%s) values (%s)", history.getFields(),history.getValues()));
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @return List String of history
     */
    public String selectAll(){
        String sql = String.format("SELECT date, time, content FROM history");
        StringBuilder result = new StringBuilder();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                result.append ("Date: " + rs.getString("date") +  "\t" +
                        "at " + rs.getString("time") + "\t" +
                        "Description: " + rs.getString("content") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }



    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/db/history.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
