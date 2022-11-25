package State;

import java.sql.*;
import java.util.ArrayList;

import models.MonModel;

public class MonState {
    Statement statement;
    ConfigDatabase configDatabase = new ConfigDatabase();

    public MonState() {
        this.statement = configDatabase.getStatement();
    }

    // create
    public void insert(String tenMon) {
        try {
            String insertquery = String.format("INSERT INTO mon(tenmon) VALUES ('%s')", tenMon);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // read
    public ArrayList<MonModel> view() {
        try {
            ArrayList<MonModel> monList = new ArrayList<>();

            String insertquery = "select * from `mon` where deleted =0";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                MonModel mon = new MonModel(result.getInt(1), result.getString(2));
                monList.add(mon);
            }
            System.out.println("View success");
            return monList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
        }
    }

    // update
    public void update(int id, String tenMon) {
        try {
            String insertquery = String.format("UPDATE `mon` set `tenMon`='%s' WHERE ma = '%d'", tenMon,
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
            String insertquery = String.format("UPDATE `mon` set `deleted`=1 WHERE ma = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<MonModel> SearchText(String s) {
        try {
            ArrayList<MonModel> monList = new ArrayList<>();
            String insertquery = String
                    .format("SELECT * FROM `mon` WHERE locate('%s',tenMon) and deleted =0",
                            s);
            ResultSet result = statement.executeQuery(insertquery);

            while (result.next()) {
                MonModel mon = new MonModel(result.getInt(1), result.getString(2));
                monList.add(mon);
            }
            return monList;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
            return null;
        }
    }
}
