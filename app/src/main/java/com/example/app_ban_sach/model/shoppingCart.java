package com.example.app_ban_sach.model;

import java.io.Serializable;

public class shoppingCart implements Serializable {
    public int idsach;
    public String tensach;
    public long giasach;
    public String hinhanhsach;
    public int soluongsach;

    public shoppingCart(int idsach, String tensach, long giasach, String hinhanhsach, int soluongsach) {
        this.idsach = idsach;
        this.tensach = tensach;
        this.giasach = giasach;
        this.hinhanhsach = hinhanhsach;
        this.soluongsach = soluongsach;
    }

    public int getIdsach() {
        return idsach;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public long getGiasach() {
        return giasach;
    }

    public void setGiasach(long giasach) {
        this.giasach = giasach;
    }

    public String getHinhanhsach() {
        return hinhanhsach;
    }

    public void setHinhanhsach(String hinhanhsach) {
        this.hinhanhsach = hinhanhsach;
    }

    public int getSoluongsach() {
        return soluongsach;
    }

    public void setSoluongsach(int soluongsach) {
        this.soluongsach = soluongsach;
    }
}




























