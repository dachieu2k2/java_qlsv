package State;

import java.sql.*;
import java.util.ArrayList;

import models.NganhModel;

public class NganhState {

    Statement statement;
    ConfigDatabase configDatabase = new ConfigDatabase();

    public NganhState() {
        this.statement = configDatabase.getStatement();
    }

    // create
    public void insert(String tennganh, int makhoa) {
        try {
            String insertquery = String.format("INSERT INTO nganh(tennganh,makhoa) VALUES ('%s','%d')", tennganh,
                    makhoa);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // read
    public ArrayList<NganhModel> view() {
        try {
            ArrayList<NganhModel> nganhList = new ArrayList<>();

            String insertquery = "SELECT * FROM `nganh` INNER JOIN khoa on khoa.ma = nganh.makhoa";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                NganhModel nganh = new NganhModel(result.getInt(1), result.getString(2), result.getInt(3),
                        result.getString(5));
                nganhList.add(nganh);
            }
            System.out.println("View success");
            return nganhList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
        }
    }

    // update
    public void update(int id, String tennganh, int makhoa) {
        try {
            String insertquery = String.format("UPDATE `nganh` set `tennganh`='%s',`makhoa`='%d' WHERE ma = '%d';",
                    tennganh, makhoa,
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
            String insertquery = String.format("DELETE FROM `nganh` WHERE ma = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<NganhModel> SearchText(String s) {
        try {
            ArrayList<NganhModel> nganhList = new ArrayList<>();
            String insertquery = String
                    .format("SELECT * FROM `nganh` INNER JOIN khoa on khoa.ma = nganh.makhoa WHERE locate('%s',tennganh)",
                            s);
            ResultSet result = statement.executeQuery(insertquery);

            while (result.next()) {
                NganhModel nganh = new NganhModel(result.getInt(1), result.getString(2), result.getInt(3),
                        result.getString(5));
                nganhList.add(nganh);
            }
            return nganhList;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
            return null;
        }
    }
}
