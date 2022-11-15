package models;

import java.sql.Date;

public class SinhVienModel {
    private int masv;
    private String tensv;
    private String hosv;
    private boolean gioitinh;
    private Date ngaysinh;
    private String noisinh;
    private String diachi;
    private int manganh;
    private boolean hocbong;
    private String avatar;
    private String tennganh;

    public SinhVienModel(int masv, String tensv, String hosv, boolean gioitinh, Date ngaysinh, String noisinh,
            String diachi,
            int manganh, boolean hocbong, String avatar, String tennganh) {
        this.masv = masv;
        this.tensv = tensv;
        this.hosv = hosv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.diachi = diachi;
        this.manganh = manganh;
        this.hocbong = hocbong;
        this.avatar = avatar;
        this.tennganh = tennganh;

    }

    public String getAvatar() {
        return avatar;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getHosv() {
        return hosv;
    }

    public int getManganh() {
        return manganh;
    }

    public int getMasv() {
        return masv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public String getTensv() {
        return tensv;
    }

    public String getGioiTinh() {
        if (this.gioitinh) {

            return "Nu";
        }
        return "Nam";
    }

    public Boolean getHocBong() {
        return hocbong;
    }

    public String getTennganh() {
        return tennganh;
    }
}
