package db;
import java.sql.*;
import java.util.Hashtable;
import model.User;
public class UserDb {
    public void createUserTable(){
        {
            try
            {
                // create a database connection
                Connection connection = this.connect();
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                        "(id integer primary key autoincrement, " +
                        "username text not null UNIQUE, password text not null, " +
                        "lastname text not null, firstname text not null, " +
                        "email text not null, phonenumber text not null, address text not null)");
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

    public void addUser(User user){
        Connection connection = null;
        try
        {
            connection = this.connect();
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(String.format("insert into user(%s) values (%s)", user.getFields(),user.getValues()));
        }
        catch(SQLException e)
        {
            //handle username is existed here
            System.err.println(e.getMessage());
            System.out.println("User is already existed");
        }
    }

    public String getUserPassword(String username){
        String sql = String.format("SELECT password FROM user WHERE username = '%s'" , username);
        String result = "";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                result =  rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/db/user.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewDatabase(String name) {

        String url = "jdbc:sqlite:src/db/"+ name;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void isExisted(String username) throws SQLException {
        String sql = String.format("SELECT password FROM user WHERE username = '%s'" , username);
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

        }
    }
}
