package com.example.app_ban_sach.model;

import java.io.Serializable;

public class sachmoi implements Serializable {
    int id;
    String tensach;
    String tentacgia;
    int soluong;
    String theloai;
    Integer gia;
    String hinhanh;
    String motasach;

    public sachmoi() {
    }

    public sachmoi(int id, String tensach, String tentacgia, int soluong, String theloai, Integer gia, String hinhanh, String motasach) {
        this.id = id;
        this.tensach = tensach;
        this.tentacgia = tentacgia;
        this.soluong = soluong;
        this.theloai = theloai;
        this.gia = gia;
        this.hinhanh = hinhanh;
        this.motasach = motasach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMotasach() {
        return motasach;
    }

    public void setMotasach(String motasach) {
        this.motasach = motasach;
    }
}
