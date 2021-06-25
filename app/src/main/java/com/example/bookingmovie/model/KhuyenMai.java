package com.example.bookingmovie.model;

import java.util.Date;

public class KhuyenMai {
    private String id;
    private String tenKhuyenMai;
    private String noiDung;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    public KhuyenMai(String id, String tenKhuyenMai, String noiDung, String img) {
        this.id = id;
        this.tenKhuyenMai = tenKhuyenMai;
        this.noiDung = noiDung;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }



    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

}
