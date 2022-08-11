package com.example.duanmau.model;

public class ThuThu {
    private String maTT;
    private String hoTen;
    private String matKHau;

    public ThuThu() {
    }

    public ThuThu(String maTT, String hoTen, String matKHau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKHau = matKHau;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKHau() {
        return matKHau;
    }

    public void setMatKHau(String matKHau) {
        this.matKHau = matKHau;
    }

    @Override
    public String toString() {
        return "ThuThu{" +
                "maTT='" + maTT + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", matKHau='" + matKHau + '\'' +
                '}';
    }
}
