package com.example.app_ban_sach.model;

public class loaisach {
    public int idtheloai;
    public String theloai;
    public String hinhanh;

    public loaisach(int idtheloai, String theloai, String hinhanh) {
        this.idtheloai = idtheloai;
        this.theloai = theloai;
        this.hinhanh = hinhanh;
    }

    public int getIdtheloai() {
        return idtheloai;
    }

    public void setIdtheloai(int idtheloai) {
        this.idtheloai = idtheloai;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
