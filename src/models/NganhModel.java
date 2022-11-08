package models;

public class NganhModel {
    private int ma;
    private String tennganh;
    private int makhoa;
    private String tenkhoa;

    public NganhModel(int ma, String tennganh, int makhoa, String tenkhoa) {
        this.ma = ma;
        this.tennganh = tennganh;
        this.makhoa = makhoa;
        this.tenkhoa = tenkhoa;
    }

    public int getMa() {
        return this.ma;
    }

    public String getTenNganh() {
        return this.tennganh;
    }

    public int getMaKhoa() {
        return this.makhoa;
    }

    public String getTenKhoa() {
        return this.tenkhoa;
    }
}
