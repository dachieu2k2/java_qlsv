package models;

public class DashboardModel {
    private int masv;
    private int mamon;
    private double diem;
    private String hosv;
    private String tensv;
    private String tenmon;

    public DashboardModel(int masv, int mamon, String hosv, String tensv, double diem, String tenmon) {
        this.masv = masv;
        this.mamon = mamon;
        this.diem = diem;
        this.hosv = hosv;
        this.tensv = tensv;
        this.tenmon = tenmon;
    }

    public double getDiem() {
        return diem;
    }

    public int getMamon() {
        return mamon;
    }

    public int getMasv() {
        return masv;
    }

    public String getHosv() {
        return hosv;
    }

    public String getTenmon() {
        return tenmon;
    }

    public String getTensv() {
        return tensv;
    }
}
