import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseAction {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    private final String DB_URL = "jdbc:mysql://localhost:3306/robotDogService";
    private final String USER = "root";
    private final String PASS = "Ming@12321";
    private Connection connection = null;
    private Statement statement = null;
    

    public DatabaseAction(){
    }

    public String initializeDB() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            System.out.println("database connected");
            String command = "update Timeslots set availability='available' ";
            
            statement.executeUpdate(command);
            System.out.println("Record updated");
            
            // ResultSet result = statement.executeQuery(command);
            // while(result.next())  
            //     System.out.println( result.getString("start_time") + "\t" + result.getString("availability")); 
            connection.close();  
            return "Timeslots initialized";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error";
        }
    }
}
