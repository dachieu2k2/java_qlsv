package State;

import java.sql.*;

public class KhoaState extends ConfigDatabase {

    // create
    public void insert(String tenkhoa) {
        try {
            String insertquery = String.format("INSERT INTO khoa(tenkhoa) VALUES ('%s')", tenkhoa);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // read
    public void view() {
        try {
            String insertquery = "select * from `khoa`";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                System.out.println("ma " + result.getString(1));
                System.out.println("\tenkhoa " + result.getString(2));
                System.out.println("\n");

            }
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
        }
    }

    // update
    public void update(int id, String tenkhoa) {
        try {
            String insertquery = String.format("UPDATE `khoa` set `tenkhoa`='%s' WHERE id = '%d'", tenkhoa,
                    id);
            statement.executeUpdate(insertquery);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // delete
    public void delete(int id) {
        try {
            String insertquery = String.format("DELETE FROM `khoa` WHERE id = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SearchText(String s) {
        try {
            String insertquery = String
                    .format("SELECT * FROM `khoa` WHERE locate('%s',name)",
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
}
