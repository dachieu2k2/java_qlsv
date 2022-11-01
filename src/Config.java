import java.sql.*;

public class Config {
    // function to connect to the xampp server
    Statement statement;

    public void DatabaseConnect() {
        try {
            // Connection conn =
            // DriverManager.getConnection("jdbc:mysql://localhost/javafx", "root", "123");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "root", "");
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

    // for inserting data
    public void insert(String nameInsert, String desInsert) {
        try {
            // String insertquery = "INSERT INTO qlsv (`name`,`age`) VALUES (" + nameInsert
            // + ", " + ageInsert + ")";
            String insertquery = String.format("INSERT INTO qlsv1(name,des) VALUES ('%s',%s)", nameInsert, desInsert);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // for viewing data
    public void view() {
        try {
            String insertquery = "select * from `qlsv1`";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                // System.out.println("\n");
                System.out.println("id " + result.getString(1));
                System.out.println("\tName " + result.getString(2));
                System.out.println("\tAge " + result.getString(3));
                System.out.println("\tcreated_At " + result.getString(4));
                System.out.println("\n");

            }
            // if (result.next()) {
            // System.out.println("Value " + result.getString(2));
            // System.out.println("Value " + result.getString(3));
            // }
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
        }
    }

    public String viewbyId(int id) {
        try {
            String insertquery = String.format("select * from `text-editor` where id = %d ", id);
            ResultSet result = statement.executeQuery(insertquery);
            String h = "";
            System.out.println(result);
            while (result.next()) {
                // System.out.println("\n");
                System.out.println("id " + result.getString(1));
                System.out.println("\tName " + result.getString(2));
                System.out.println("\tAge " + result.getString(3));
                h = result.getString(3);
                System.out.println("\tcreated_At " + result.getString(4));
                System.out.println("\n");
            }
            return h;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return ex.getMessage();
        }
    }

    // view sort by
    public void viewOrderByAge(String field, String sortby) {
        try {

            String insertquery = String.format("select * from `qlsv1` order by %s %s", field, sortby);
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                System.out.println("\n");
                System.out.println("id " + result.getString(1));
                System.out.println("Name " + result.getString(2));
                System.out.println("Age " + result.getString(3));
                System.out.println("created_At " + result.getString(4));
                System.out.println("\n");

            }
            // if (result.next()) {
            // System.out.println("Value " + result.getString(2));
            // System.out.println("Value " + result.getString(3));
            // }
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
        }
    }

    public void SearchText(String s) {
        try {
            // ADD QUERY THIS FIRST : ALTER TABLE `qlsv1` ADD FULLTEXT (name);

            // @Search text
            // String insertquery = String
            // .format("SELECT * FROM `qlsv1` WHERE MATCH(name) AGAINST ('%s' IN NATURAL
            // LANGUAGE MODE WITH QUERY EXPANSION);",
            // s);

            // @Search character
            String insertquery = String
                    .format("SELECT * FROM `qlsv1` WHERE locate('%s',name)",
                            s);
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                System.out.println("\n");
                System.out.println("id " + result.getString(1));
                System.out.println("Name " + result.getString(2));
                System.out.println("Age " + result.getString(3));
                System.out.println("created_At " + result.getString(4));
                System.out.println("\n");

            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
        }
    }

    // to update data
    public void update(int id, String name, int age) {
        try {
            String insertquery = String.format("UPDATE `qlsv1` set `name`='%s',`age`='%d' WHERE id = '%d'", name, age,
                    id);
            statement.executeUpdate(insertquery);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // to delete data
    public void delete(int id) {
        try {
            String insertquery = String.format("DELETE FROM `qlsv1` WHERE id = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}