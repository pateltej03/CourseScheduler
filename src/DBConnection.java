
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
@author: Tej Jaideep Patel, tfp5304@psu.edu , 975793441
**/

public class DBConnection {
    private static Connection connection;
    private static final String user = "java";
    private static final String password = "java";
    private static final String database = "jdbc:derby://localhost:1527/CourseSchedulerDBTejJaideepPateltfp5304;create=true";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(database, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);

            }
        }
        return connection;
    }

    
}
