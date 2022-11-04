package State;

import java.sql.*;
import java.util.ArrayList;

import models.KhoaModel;

public class NganhState {
    Statement statement;
    public ArrayList<KhoaModel> khoaList = new ArrayList<>();

    NganhState(Statement statement) {
        this.statement = statement;
    }

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
    public ArrayList<KhoaModel> view() {
        try {
            String insertquery = "select * from `khoa`";
            ResultSet result = statement.executeQuery(insertquery);
            // while (result.next()) {
            // System.out.println("ma " + result.getString(1));
            // System.out.println("\ntenkhoa " + result.getString(2));
            // System.out.println("\n");

            // }
            while (result.next()) {
                KhoaModel khoa = new KhoaModel(result.getInt(1), result.getString(2));
                khoaList.add(khoa);

            }
            return khoaList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
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
