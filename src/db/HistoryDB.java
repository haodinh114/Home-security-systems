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
                        "content text not null, type_alert text not null, " +
                        "isResolved INTEGER not null" +
                        ")");
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

    public String getNotResolvedRecords(){
        String sql = String.format("SELECT date, time, content FROM history ORDER BY\n" +
                "    id DESC where isResolved = 0" +
                ";");
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

    public String select10Records(){
        String sql = String.format("SELECT date, time, content FROM history ORDER BY\n" +
                "    id DESC LIMIT 10" +
                ";");
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

    public String selectLastRecord(){
        String sql = String.format("SELECT date, time, content FROM history ORDER BY\n" +
                "    id DESC LIMIT 1" +
                ";");
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

    public void updateResolvedRecord(int id, int value){
//        UPDATE tasks SET priority = ? , begin_date = ? ,end_date = ? WHERE id = ?
        String sql = String.format("UPDATE history SET isResolved = %s WHERE id = %s ;", value, id);
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
