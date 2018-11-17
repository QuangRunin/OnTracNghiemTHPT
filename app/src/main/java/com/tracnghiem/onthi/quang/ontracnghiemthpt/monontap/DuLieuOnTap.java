package com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap;

public class DuLieuOnTap {
    private int _id;
    private String tieude;
    private String noidung;
    private int monontap;

    public DuLieuOnTap(int _id, String tieude, String noidung, int monontap) {
        this._id = _id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.monontap = monontap;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getMonontap() {
        return monontap;
    }

    public void setMonontap(int monontap) {
        this.monontap = monontap;
    }
}
