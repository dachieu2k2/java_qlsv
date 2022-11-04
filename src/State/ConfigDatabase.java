package State;

import java.sql.*;

public class ConfigDatabase {
    Statement statement;

    ConfigDatabase() {

        try {
            // Connection conn =
            // DriverManager.getConnection("jdbc:mysql://localhost/javafx", "root", "123");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysv", "root", "");
            /*
             * here javafx is the name of the database and root is the username of
             * your xampp server and the field which is blank is for password.
             * Because I haven't set the password. I have left it blank
             */
            statement = conn.createStatement();
            System.out.print("Database Connected\n");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("Database Not Connected\n");
            System.exit(0);
        }

    }
}
