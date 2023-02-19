import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/robotDogService";
    
    static final String USER = "root";
    static final String PASS = "Ming@12321";
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String command = "update Timeslots set availability='occupied' ";
            statement.executeUpdate(command);
            // ResultSet result = statement.executeQuery(command);
            // while(result.next())  
            //     System.out.println( result.getString("start_time") + "\t" + result.getString("availability")); 
            connection.close();  
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
