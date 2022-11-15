package State;

import java.sql.*;
import java.util.ArrayList;

import models.DashboardModel;

public class DashboardState {
    Statement statement;
    ConfigDatabase configDatabase = new ConfigDatabase();

    public DashboardState() {
        this.statement = configDatabase.getStatement();
    }

    // create
    public void insert(int masv, int mamon, double diem) {
        try {
            String insertquery = String.format("INSERT INTO ketqua(masv,mamon,diem) VALUES ('%d','%d','%f')", masv,
                    mamon, diem);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // read
    public ArrayList<DashboardModel> view() {
        try {
            ArrayList<DashboardModel> diemList = new ArrayList<>();

            String insertquery = "SELECT ketqua.masv,ketqua.mamon,`hosv`,`tensv`,`diem`,`tenmon` FROM `ketqua` JOIN sinhvien on sinhvien.masv = ketqua.masv JOIN mon on ketqua.mamon = mon.ma ORDER BY `diem` DESC";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                DashboardModel diem = new DashboardModel(
                        result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
                        result.getDouble(5), result.getString(6));
                diemList.add(diem);
            }
            System.out.println("View success");
            return diemList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
        }
    }

    // update
    public void update(int masv, int mamon, double diem) {
        try {
            String insertquery = String.format("UPDATE `ketqua` set `diem`='%f' WHERE masv = '%d' and mamon='%d';",
                    diem, masv, mamon);
            statement.executeUpdate(insertquery);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // delete
    public void delete(int masv, int mamon) {
        try {
            String insertquery = String.format("DELETE FROM `ketqua` WHERE masv = '%d' and mamon='%d'", masv, mamon);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<DashboardModel> SearchText(String s) {
        try {
            ArrayList<DashboardModel> diemList = new ArrayList<>();
            String insertquery = String
                    .format("SELECT ketqua.masv,ketqua.mamon,`hosv`,`tensv`,`diem`,`tenmon` FROM `ketqua` JOIN sinhvien on sinhvien.masv = ketqua.masv JOIN mon on ketqua.mamon = mon.ma WHERE locate('%s',tensv) ORDER BY `diem` DESC",
                            s);
            ResultSet result = statement.executeQuery(insertquery);

            while (result.next()) {
                DashboardModel diem = new DashboardModel(
                        result.getInt(1), result.getInt(2), result.getString(3), result.getString(4),
                        result.getDouble(5), result.getString(6));
                diemList.add(diem);
            }
            return diemList;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
            return null;
        }
    }
}
