package models;

public class KhoaModel {
    private int ma;
    private String tenkhoa;

    public KhoaModel(int ma, String tenkhoa) {
        this.ma = ma;
        this.tenkhoa = tenkhoa;
    }

    public int getMa() {
        return this.ma;
    }

    public String getTenKhoa() {
        return this.tenkhoa;
    }

}
