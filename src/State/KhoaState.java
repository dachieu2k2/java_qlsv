package State;

import java.sql.*;
import java.util.ArrayList;

import models.KhoaModel;

public class KhoaState {

    Statement statement;
    ConfigDatabase configDatabase = new ConfigDatabase();

    public KhoaState() {
        this.statement = configDatabase.getStatement();
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
            ArrayList<KhoaModel> khoaList = new ArrayList<>();

            String insertquery = "select * from `khoa`";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                KhoaModel khoa = new KhoaModel(result.getInt(1), result.getString(2));
                khoaList.add(khoa);
            }
            System.out.println("View success");
            return khoaList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
        }
    }

    // update
    public void update(int id, String tenkhoa) {
        try {
            String insertquery = String.format("UPDATE `khoa` set `tenkhoa`='%s' WHERE ma = '%d'", tenkhoa,
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
            String insertquery = String.format("DELETE FROM `khoa` WHERE ma = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<KhoaModel> SearchText(String s) {
        try {
            ArrayList<KhoaModel> khoaList = new ArrayList<>();
            String insertquery = String
                    .format("SELECT * FROM `khoa` WHERE locate('%s',tenkhoa)",
                            s);
            ResultSet result = statement.executeQuery(insertquery);

            while (result.next()) {
                KhoaModel khoa = new KhoaModel(result.getInt(1), result.getString(2));
                khoaList.add(khoa);
            }
            return khoaList;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
            return null;
        }
    }

}
