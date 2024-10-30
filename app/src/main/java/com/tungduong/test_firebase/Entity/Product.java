package com.tungduong.test_firebase.Entity;

public class Product {
    public String id;
    public String tensp;
    public int soLuong;
    public Float giaTien;
    public String idLoaiDoAn;
    public String moTa;

    public Product(String id, String tensp, int soLuong, Float giaTien, String idLoaiDoAn, String moTa) {
        this.id = id;
        this.tensp = tensp;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.idLoaiDoAn = idLoaiDoAn;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }

    public String getIdLoaiDoAn() {
        return idLoaiDoAn;
    }

    public void setIdLoaiDoAn(String idLoaiDoAn) {
        this.idLoaiDoAn = idLoaiDoAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
