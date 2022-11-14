package State;

import java.sql.*;
import java.util.ArrayList;

import models.SinhVienModel;

public class SinhVienState {
    Statement statement;
    ConfigDatabase configDatabase = new ConfigDatabase();

    public SinhVienState() {
        this.statement = configDatabase.getStatement();
    }

    // create
    public void insert(String tensv, String hosv, int gioitinh, Date ngaysinh, String noisinh,
            String diachi,
            int manganh, int hocbong, String avatar) {
        try {
            String insertquery = String.format(
                    "INSERT INTO sinhvien(tensv,hosv,gioitinh,ngaysinh,noisinh,diachi,manganh,hocbong,avatar) VALUES ('%s','%s','%d','%s','%s','%s','%d','%d','%s')",
                    tensv, hosv, gioitinh, ngaysinh, noisinh,
                    diachi,
                    manganh, hocbong, avatar);
            statement.executeUpdate(insertquery);
            System.out.print("Inserted");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nNot Inserted");
        }
    }

    // read
    public ArrayList<SinhVienModel> view() {
        try {
            ArrayList<SinhVienModel> sinhVienList = new ArrayList<>();

            String insertquery = "SELECT * FROM `sinhvien` INNER JOIN nganh on sinhvien.manganh = nganh.ma";
            ResultSet result = statement.executeQuery(insertquery);
            while (result.next()) {
                SinhVienModel sinhvien = new SinhVienModel(result.getInt(1), result.getString(2), result.getString(3),
                        result.getBoolean(4),
                        result.getDate(5), result.getString(6), result.getString(7), result.getInt(8),
                        result.getBoolean(9), result.getString(10), result.getString(12));
                sinhVienList.add(sinhvien);
            }
            System.out.println("View success");
            return sinhVienList;
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
            return null;
        }
    }

    // update
    public void update(int id, String tensv, String hosv, int gioitinh, Date ngaysinh, String noisinh,
            String diachi,
            int manganh, int hocbong, String avatar) {
        try {
            String insertquery = String.format(
                    "UPDATE `sinhvien` set `tensv`='%s',`hosv`='%s',`gioitinh`='%d',`ngaysinh`='%s',`noisinh`='%s',`diachi`='%s',`manganh`='%s',`hocbong`='%d',`avatar`='%s' WHERE masv = '%d';",
                    tensv, hosv, gioitinh, ngaysinh, noisinh,
                    diachi,
                    manganh, hocbong, avatar,
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
            String insertquery = String.format("DELETE FROM `sinhvien` WHERE masv = '%d'", id);
            statement.executeUpdate(insertquery);
            System.out.println("Deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<SinhVienModel> SearchText(String s) {
        try {
            ArrayList<SinhVienModel> sinhVienList = new ArrayList<>();
            String insertquery = String
                    .format("SELECT * FROM `sinhvien` INNER JOIN nganh on sinhvien.manganh = nganh.ma WHERE locate('%s',tensv)",
                            s);
            ResultSet result = statement.executeQuery(insertquery);

            while (result.next()) {
                SinhVienModel sinhvien = new SinhVienModel(result.getInt(1), result.getString(2), result.getString(3),
                        result.getBoolean(4),
                        result.getDate(5), result.getString(6), result.getString(7), result.getInt(8),
                        result.getBoolean(9), result.getString(10), result.getString(12));
                sinhVienList.add(sinhvien);
            }
            return sinhVienList;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            System.out.print("\nProblem with data");
            return null;
        }
    }

}
