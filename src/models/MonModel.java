package models;

public class MonModel {
    private int ma;
    private String tenmon;

    public MonModel(int ma, String tenmon) {
        this.ma = ma;
        this.tenmon = tenmon;
    }

    public int getMa() {
        return this.ma;
    }

    public String getTenMon() {
        return this.tenmon;
    }
}
