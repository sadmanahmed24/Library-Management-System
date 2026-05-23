import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            // Oracle Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Database URL
            String url = "jdbc:oracle:thin:@localhost:1521:xe";

            // Oracle username/password
            String username = "system";

            String password = "123";

            Connection con = DriverManager.getConnection(
                    url,
                    username,
                    password);

            System.out.println(
                    "Database Connected Successfully");

            return con;

        } catch (Exception e) {

            System.out.println(
                    "Database Connection Failed");

            e.printStackTrace();

            return null;
        }
    }
}
