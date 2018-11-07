package com.tracnghiem.onthi.quang.ontracnghiemthpt.thongke;

public class Diem {
    private int id;
    private String hoten;
    private String monhoc;
    private String đungsai;
    private double diem;

    public Diem(int id, String hoten, String monhoc, String đungsai, double diem) {
        this.id = id;
        this.hoten = hoten;
        this.monhoc = monhoc;
        this.đungsai = đungsai;
        this.diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public String getĐungsai() {
        return đungsai;
    }

    public void setĐungsai(String đungsai) {
        this.đungsai = đungsai;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}

