package com.example.doanltdd.add;

public class GiaoDich {
    private int loaiGiaoDich;
    private int soTien;
    private String noiDung;
    private String ngayThang;

    public GiaoDich(int loaiGiaoDich, int soTien, String noiDung, String ngayThang) {
        this.loaiGiaoDich = loaiGiaoDich;
        this.soTien = soTien;
        this.noiDung = noiDung;
        this.ngayThang = ngayThang;
    }

    public int getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(int loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }
}
